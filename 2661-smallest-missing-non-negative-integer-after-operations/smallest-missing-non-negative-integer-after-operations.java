class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int m = nums[i] % value;
            if (m < 0)
                m += value;
            freq.put(m, freq.getOrDefault(m, 0) + 1);
        }

        int ret = 0;
        while (true) {
            if (!freq.containsKey(ret % value))
                break;
            freq.put(ret % value, freq.get(ret % value) - 1);
            if (freq.get(ret % value) == 0) 
                freq.remove(ret % value);
            ret++;
        }

        return ret;
    }
}