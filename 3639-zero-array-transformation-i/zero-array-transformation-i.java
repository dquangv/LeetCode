class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] count = new int[n + 1];

        for (int[] q : queries) {
            count[q[0]] += 1;
            count[q[1] + 1] -= 1;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += count[i];
            count[i] = sum;
            if (nums[i] > count[i])
                return false;

        }

        return true;
    }
}