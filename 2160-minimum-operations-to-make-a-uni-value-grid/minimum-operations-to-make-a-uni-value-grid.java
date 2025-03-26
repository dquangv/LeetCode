class Solution {
    public int minOperations(int[][] grid, int x) {
        int[] arr = new int[grid.length * grid[0].length];
        int remainder = grid[0][0] % x;
        int operations = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] % x != remainder)
                    return -1;

                arr[i * grid[0].length + j] = grid[i][j];
            }
        }

        Arrays.sort(arr);
        int median = arr[(grid.length * grid[0].length) / 2];

        for (int num : arr) {
            operations += Math.abs(num - median) / x;
        }

        return operations;
    }
}