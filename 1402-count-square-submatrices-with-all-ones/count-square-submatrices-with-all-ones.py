class Solution:
    def countSquares(self, matrix: List[List[int]]) -> int:
        result = 0

        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j] == 1:
                    # Look at top, left, and diagonal
                    top = matrix[i-1][j] if i > 0 else 0
                    left = matrix[i][j-1] if j > 0 else 0
                    diag = matrix[i-1][j-1] if i > 0 and j > 0 else 0

                    # Update cell with max square size ending here
                    if i > 0 and j > 0:
                        matrix[i][j] += min(top, left, diag)

                    # Count all squares ending here
                    result += matrix[i][j]

        return result