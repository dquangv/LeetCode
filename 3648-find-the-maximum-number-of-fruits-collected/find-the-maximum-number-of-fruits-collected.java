class Solution {
    public int maxCollectedFruits(int[][] A) {
        int n = A.length;

        // Step 1: Mark inaccessible cells with 0 based on triangle-shaped constraints
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // If cell is in the upper triangle region (not on the paths)
                if (i < j && j < n - 1 - i)
                    A[i][j] = 0; // Remove fruits, not reachable by any child
                // If cell is in the lower triangle region (also not on the paths)
                if (j < i && i < n - 1 - j)
                    A[i][j] = 0; // Remove fruits, not reachable by any child
            }
        }

        // Step 2: First child - moves diagonally from top-left (0, 0) to bottom-right (n-1, n-1)
        int res = 0;
        for (int i = 0; i < n; ++i)
            res += A[i][i]; // This child moves strictly on the diagonal (i, i)

        // Step 3: Second child - moves from top-right (0, n-1) to bottom-right (n-1, n-1)
        // Using dynamic programming from top to bottom
        for (int i = 1; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                // Accumulate maximum fruits from 3 possible paths:
                // (i-1, j-1), (i-1, j), (i-1, j+1)
                A[i][j] += Math.max(
                        A[i - 1][j - 1],
                        Math.max(
                                A[i - 1][j],
                                (j + 1 < n) ? A[i - 1][j + 1] : 0));

        // Step 4: Third child - moves from bottom-left (n-1, 0) to bottom-right (n-1, n-1)
        // Using dynamic programming from left to right
        for (int j = 1; j < n; ++j)
            for (int i = j + 1; i < n; ++i)
                // Accumulate maximum fruits from 3 possible paths:
                // (i-1, j-1), (i, j-1), (i+1, j-1)
                A[i][j] += Math.max(
                        A[i - 1][j - 1],
                        Math.max(
                                A[i][j - 1],
                                (i + 1 < n) ? A[i + 1][j - 1] : 0));

        // Step 5: Combine all collected fruits:
        // - res = fruits collected on the diagonal by the first child
        // - A[n-1][n-2] = ending point of second child
        // - A[n-2][n-1] = ending point of third child
        return res + A[n - 1][n - 2] + A[n - 2][n - 1];
    }
}