class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);

        return count(nums, upper) - count(nums, lower - 1);
    }

    public long count(int[] nums, int x) {
        int left = 0, right = nums.length - 1;
        long result = 0;

        while (left < right) {
            if (nums[left] + nums[right] > x) right--;
            else result += right - left++;
        }

        return result;
    }
}