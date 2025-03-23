class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = (int) 1e9 + 7;

        List<List<int[]>> adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            adj.get(road[0]).add(new int[]{road[1], road[2]});
            adj.get(road[1]).add(new int[]{road[0], road[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        int[] ways = new int[n];
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currDist = curr[0];
            int node = (int) curr[1];

            if (currDist > dist[node]) continue;

            for (int[] neighbor : adj.get(node)) {
                int nextNode = neighbor[0];
                long time = neighbor[1];
                long newDist = currDist + time;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    ways[nextNode] = ways[node];
                    pq.offer(new long[]{newDist, nextNode});
                } else if (newDist == dist[nextNode]) {
                    ways[nextNode] = (ways[nextNode] + ways[node]) % mod;
                }
            }
        }

        return ways[n-1];
    }
}