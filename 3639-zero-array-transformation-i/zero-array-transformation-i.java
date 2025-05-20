class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] count = new int[n + 1];

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            count[l] += 1;
            if (r + 1 < n) {
                count[r + 1] -= 1;
            }
        }

        for (int i = 1; i < n; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > count[i]) {
                return false;
            }
        }

        return true;
    }
}