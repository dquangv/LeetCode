class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int left = 0, right = queries.length;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canBeZero(nums, queries, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private boolean canBeZero(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        long[] diff = new long[n + 2];
        
        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            diff[l] += val;
            diff[r + 1] -= val;
        }

        long currSum = 0;
        for (int i = 0; i < n; i++) {
            currSum += diff[i];
            if (currSum < nums[i]) {
                return false;
            }
        }

        return true;
    }
}

