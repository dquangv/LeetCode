class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long L = nums[0], maxD = L - nums[1], result = Math.max(0L, maxD * nums[2]);

        for (int i = 3; i < n; i++) {
            L = Math.max(L, (long)nums[i - 2]);
            maxD = Math.max(maxD, L - nums[i - 1]);
            result = Math.max(result, maxD * nums[i]);
        }

        return result;
    }
}