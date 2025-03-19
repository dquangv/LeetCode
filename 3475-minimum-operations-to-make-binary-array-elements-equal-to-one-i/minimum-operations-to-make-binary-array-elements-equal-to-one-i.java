class Solution {
    public int minOperations(int[] nums) {
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 1)
                continue;

            nums[i] ^= 1;
            nums[i + 1] ^= 1;
            nums[i + 2] ^= 1;
            result++;
        }

        if (nums[nums.length - 2] == 0 || nums[nums.length - 1] == 0)
            return -1;

        return result;
    }
}