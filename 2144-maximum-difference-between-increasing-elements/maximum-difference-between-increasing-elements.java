class Solution {
    public int maximumDifference(int[] nums) {
        int min = nums[0], max = 0;
        for (int i = 1; i < nums.length; i++)
            if (nums[i] < min)
                min = nums[i];
            else
                max = max > nums[i] - min ? max : nums[i] - min;

        return max != 0 ? max : -1;
    }
}