class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        if not mat:
            return 0
        m, n = len(mat), len(mat[0])
        result = 0

        # --- Step 1: Preprocess using RLE (Run-Length Encoding of consecutive 1s per row) ---
        # mat[i][j] will store the number of consecutive 1s ending at column j in row i
        for i in range(m):
            for j in range(n):
                if j > 0 and mat[i][j]:  # if current cell is 1, add length from left
                    mat[i][j] = mat[i][j - 1] + 1

        # --- Step 2: Count rectangles ---
        # Iterate over each cell (i, j) as the "top-right" corner of possible submatrices
        for i in range(m):
            for j in range(n):
                ans = mat[i][j]
                # Expand downward row by row
                for k in range(i, m):
                    # The width of rectangle is limited by the minimum run-length so far
                    ans = min(ans, mat[k][j])
                    # Add number of rectangles ending at (k,j) with top at (i,j)
                    result += ans

        return result
