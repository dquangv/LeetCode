class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length, left = 0;
        long pairs = 0, result = 0;

        for (int right = 0; right < n; right++) {
            int val = nums[right];
            int count = freq.getOrDefault(val, 0);
            
            pairs += count;
            freq.put(val, count + 1);

            while (pairs >= k) {
                result += n - right;

                int leftVal = nums[left];
                int leftCount = freq.get(leftVal);

                pairs -= leftCount - 1;
                freq.put(leftVal, leftCount - 1);
                left++;
            }
        }

        return result;
    }
}