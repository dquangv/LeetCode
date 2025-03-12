class Solution {
    public int maximumCount(int[] nums) {
        return Math.max(findFirstGreaterOrEqual(nums, 0), nums.length - findFirstGreaterOrEqual(nums, 1));
    }

    private int findFirstGreaterOrEqual(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (right + left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}