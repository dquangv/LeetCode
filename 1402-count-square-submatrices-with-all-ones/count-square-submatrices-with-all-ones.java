class Solution {
    public int countSquares(int[][] matrix) {
        int result = 0;

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 1) {
                    // Look at neighbors: top, left, and top-left (diagonal)
                    int top = (i == 0) ? 0 : matrix[i - 1][j];
                    int left = (j == 0) ? 0 : matrix[i][j - 1];
                    int diag = (i == 0 || j == 0) ? 0 : matrix[i - 1][j - 1];

                    // Update current cell with the size of the largest square ending here
                    matrix[i][j] += Math.min(Math.min(top, left), diag);

                    // Add to result: each cell contributes the number of squares that end at it
                    result += matrix[i][j];
                }
        
        return result;
    }
}
