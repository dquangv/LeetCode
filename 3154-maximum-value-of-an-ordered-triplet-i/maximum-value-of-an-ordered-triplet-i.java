class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long L = nums[0], maxD = L - nums[1], ans = Math.max(0L, maxD * nums[2]);

        for (int k = 3; k < n; k++) {
            L = Math.max(L, (long) nums[k - 2]);
            maxD = Math.max(maxD, L - nums[k - 1]);
            ans = Math.max(ans, maxD * nums[k]);
        }

        return ans;
    }
}