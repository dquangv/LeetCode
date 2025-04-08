class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        boolean[] distinct = new boolean[101];

        for (int i = n - 1; i >= 0; i--) {
            if (distinct[nums[i]]) return i / 3 + 1;
            distinct[nums[i]] = true;
        }

        return 0;
    }
}