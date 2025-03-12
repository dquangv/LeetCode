class Solution {
    public int maximumCount(int[] nums) {
        int firstNonNegativeIndex = findFirstGreaterOrEqual(nums, 0);
        int firstPositiveIndex = findFirstGreaterOrEqual(nums, 1);

        int negatives = firstNonNegativeIndex;
        int positives = nums.length - firstPositiveIndex;

        return Math.max(negatives, positives);
    }

    private int findFirstGreaterOrEqual(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}