class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        HashMap<Integer, Integer> range = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        int minrange = (int) 1e9;
        int maxrange = (int) -1e9;

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            int l = num - k;
            int r = num + k;

            minrange = Math.min(minrange, l);
            maxrange = Math.max(maxrange, r);
            range.put(l, range.getOrDefault(l, 0) + 1);
            range.put(r + 1, range.getOrDefault(r + 1, 0) - 1);
        }

        int ans = 1;
        for (int i = minrange; i <= maxrange; i++) {
            range.put(i, range.getOrDefault(i - 1, 0) + range.getOrDefault(i, 0));
            int cnt = freq.getOrDefault(i, 0);
            int maxfreq = range.get(i) - cnt;
            maxfreq = Math.min(maxfreq, numOperations);
            ans = Math.max(ans, cnt + maxfreq);
        }

        return ans;
    }
}