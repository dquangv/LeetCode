class Solution:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        r, c = len(mat), len(mat[0])
        ans = [0] * (r * c)
        flip, idx = 0, 0  # flip = direction toggle, idx = index in result array

        # Loop through each possible diagonal (total diagonals = r + c - 1)
        for d in range(r + c - 1):
            if flip == 0:  # going UP-RIGHT
                i = min(d, r - 1)  # row index starts from min(d, last row)
                j = d - i  # column index accordingly
                while i >= 0 and j < c:  # move up-right
                    ans[idx] = mat[i][j]
                    i -= 1
                    j += 1
                    idx += 1
            else:  # going DOWN-LEFT
                j = min(d, c - 1)  # column index starts from min(d, last column)
                i = d - j  # row index accordingly
                while j >= 0 and i < r:  # move down-left
                    ans[idx] = mat[i][j]
                    i += 1
                    j -= 1
                    idx += 1

            flip = 1 - flip  # toggle direction for next diagonal
        return ans