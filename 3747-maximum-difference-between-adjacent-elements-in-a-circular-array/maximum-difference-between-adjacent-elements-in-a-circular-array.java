class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; i++)
            result = Math.abs(nums[i]- nums[i + 1]) > result ? Math.abs(nums[i]- nums[i + 1]) : result;

        return Math.abs(nums[nums.length - 1] - nums[0]) > result ? Math.abs(nums[nums.length - 1] - nums[0]) : result;
    }
}