class Solution {
    public int minimumArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // Initialize boundaries
        int l = Integer.MAX_VALUE, u = Integer.MAX_VALUE;
        int r = Integer.MIN_VALUE, d = Integer.MIN_VALUE;

        // Traverse grid
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 1) {
                    l = Math.min(l, j); // left boundary
                    u = Math.min(u, i); // upper boundary
                    r = Math.max(r, j); // right boundary
                    d = Math.max(d, i); // bottom boundary
                }

        return (r - l + 1) * (d - u + 1);
    }
}