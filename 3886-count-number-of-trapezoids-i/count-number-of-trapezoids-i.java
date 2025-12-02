class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        int mod = 1000000007;

        HashMap<Long, Long> hm = new HashMap<>();
        for (int i = 0; i < n; i++)
            hm.put((long) points[i][1], hm.getOrDefault((long) points[i][1], 0L) + 1);

        List<Long> al = new ArrayList<Long>();
        for (long key : hm.keySet())
            if (hm.get(key) >= 2)
                al.add(((hm.get(key) * (hm.get(key) - 1)) / 2) % mod);

        long total = 0;

        long ans = 0;
        for (int i = 0; i < al.size(); i++) {
            ans = (ans + al.get(i) * total % mod) % mod;
            total = (total + al.get(i)) % mod;
        }

        return (int) ans;
    }
}