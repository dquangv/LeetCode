class Packet {
    int source, destination, timestamp;

    Packet(int s, int d, int t) {
        source = s;
        destination = d;
        timestamp = t;
    }
}

class Router {
    private int limit; // max memory limit (number of packets)
    private Deque<Packet> q; // FIFO queue for packets
    private Map<Integer, List<Integer>> m; // destination -> sorted list of timestamps
    private Set<String> seen; // to detect duplicates

    public Router(int memoryLimit) {
        this.limit = memoryLimit;
        this.q = new ArrayDeque<>();
        this.m = new HashMap<>();
        this.seen = new HashSet<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "#" + destination + "#" + timestamp;
        if (seen.contains(key))
            return false; // duplicate packet

        Packet p = new Packet(source, destination, timestamp);
        q.addLast(p);
        seen.add(key);

        m.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

        // Ensure memory limit (remove oldest packet if overflow)
        if (q.size() > limit) {
            Packet oldest = q.pollFirst();
            String oldestKey = oldest.source + "#" + oldest.destination + "#" + oldest.timestamp;
            seen.remove(oldestKey);

            List<Integer> list = m.get(oldest.destination);
            if (list != null && !list.isEmpty())
                list.remove(0); // remove oldest timestamp
        }

        return true;
    }

    public int[] forwardPacket() {
        if (q.isEmpty())
            return new int[0];

        Packet p = q.pollFirst();
        String key = p.source + "#" + p.destination + "#" + p.timestamp;
        seen.remove(key);

        List<Integer> list = m.get(p.destination);
        if (list != null && !list.isEmpty())
            list.remove(0);

        return new int[] { p.source, p.destination, p.timestamp };
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> list = m.get(destination);
        if (list == null || list.isEmpty())
            return 0;

        // Binary search lower bound (first index >= startTime)
        int left = lowerBound(list, startTime);
        // Binary search upper bound (first index > endTime)
        int right = upperBound(list, endTime);

        return right - left;
    }

    // Find first index >= target
    private int lowerBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    // Find first index > target
    private int upperBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) > target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */