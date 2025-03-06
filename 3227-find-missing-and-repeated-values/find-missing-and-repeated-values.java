class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] arr = new int[grid.length * grid.length + 1];
        int[] result = new int[2];
        int sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                sum += i * grid.length + j + 1;

                if (arr[grid[i][j]] != 0) {
                    result[0] = grid[i][j];
                } else {
                    sum -= grid[i][j];
                }

                arr[grid[i][j]] = grid[i][j];
            }
        }

        result[1] = sum;

        return result;
    }
}