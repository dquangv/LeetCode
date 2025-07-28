class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int max = 0;

        // Step 1: Find the maximum possible OR value across any subset
        // This will be our target to compare against later
        for (int n : nums)
            max |= n; // Bitwise OR accumulation of all elements

        // Step 2: Start DFS to count all subsets whose OR equals max
        return dfs(nums, 0, 0, max);
    }

    // Recursive DFS function to explore all subsets
    private int dfs(int[] nums, int i, int or, int max) {
        // Base case: we've reached the end of the array
        if (i == nums.length)
            // If the OR of this subset equals the maximum, count it as 1
            return or == max ? 1 : 0;

        // Choice 1: include nums[i] in the current subset (OR it)
        int take = dfs(nums, i + 1, or | nums[i], max);

        // Choice 2: exclude nums[i] from the current subset
        int skip = dfs(nums, i + 1, or, max);

        // Return the total number of valid subsets from both choices
        return take + skip;
    }
}
