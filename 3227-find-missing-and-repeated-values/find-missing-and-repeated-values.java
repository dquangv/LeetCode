class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] arr = new int[n * n + 1];
        int[] result = new int[2];
        long expectedSum = (long) n * n * (n * n + 1) / 2;
        int actualSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                actualSum += grid[i][j];

                if (arr[grid[i][j]] != 0) {
                    result[0] = grid[i][j];
                }

                arr[grid[i][j]] = 1;
            }
        }

        result[1] = (int) (expectedSum - (actualSum - result[0]));

        return result;
    }
}