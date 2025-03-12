class Solution {
    public int maximumCount(int[] nums) {
        int result = 0;
        int zero = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <0) {
                result++;
            } else if (nums[i] == 0) {
                zero++;
            } else {
                break;
            }
        }

        return (result > nums.length - result - zero) ? result : nums.length - result - zero;
    }
}