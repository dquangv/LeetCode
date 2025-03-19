class Solution {
    public int minOperations(int[] nums) {
        int result = 0;
        boolean allOne = true;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 1)
                continue;

            nums[i] ^= 1;
            nums[i + 1] ^= 1;
            nums[i + 2] ^= 1;
            result++;
            allOne = false;
        }

        if (nums[nums.length - 2] == 0 || nums[nums.length - 1] == 0)
            return -1;

        if (allOne) return 0;

        return result > 0 ? result : -1;
    }
}