class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int result = Math.abs(nums[nums.length - 1] - nums[0]);
        for (int i = 0; i < nums.length - 1; i++)
            result = Math.abs(nums[i]- nums[i + 1]) > result ? Math.abs(nums[i]- nums[i + 1]) : result;

        return result;
    }
}