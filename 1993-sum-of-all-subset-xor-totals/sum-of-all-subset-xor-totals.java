class Solution {
    int sum = 0;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);

        return sum;
    }

    private void dfs(int[] nums, int i, int curXor) {
        if (i == nums.length) {
            sum += curXor;

            return;
        }

        dfs(nums, i + 1, curXor ^ nums[i]);
        dfs(nums, i + 1, curXor);
    }
}