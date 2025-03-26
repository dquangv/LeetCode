class Solution {
    public int minOperations(int[][] grid, int x) {
        int[] arr = new int[grid.length * grid[0].length];

        int index = 0;
        for (int[] row : grid) {
            for (int num : row) {
                arr[index++] = num;
            }
        }

        Arrays.sort(arr);
        int median = arr[(grid.length * grid[0].length) / 2];
        int remainder = arr[0] % x;
        int operations = 0;
        for (int num : arr) {
            if (num % x != remainder)
                return -1;

            operations += Math.abs(num - median) / x;
        }

        return operations;
    }
}