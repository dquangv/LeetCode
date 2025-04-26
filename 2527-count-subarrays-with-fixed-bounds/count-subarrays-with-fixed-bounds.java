class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int maxPos = -1, minPos = -1;
        int leftBound = 0;
        int n = nums.length;

        for (int right = 0; right < n; right++) {
            int x = nums[right];
            if (x < minK || x > maxK) {
                leftBound = right + 1; // reset left bound
                maxPos = -1;
                minPos = -1;
                continue;
            }

            if (x == maxK) maxPos = right;
            if (x == minK) minPos = right;

            int validStart = Math.min(maxPos, minPos);
            if (validStart >= leftBound) {
                ans += validStart - leftBound + 1;
            }
        }

        return ans;
    }
}