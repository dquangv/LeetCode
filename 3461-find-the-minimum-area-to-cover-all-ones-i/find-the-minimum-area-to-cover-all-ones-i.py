class Solution:
    def minimumArea(self, grid: List[List[int]]) -> int:
        n = len(grid) 
        m = len(grid[0])

        # Initialize boundaries:
        # l = leftmost column of a '1'
        # r = rightmost column of a '1'
        # u = uppermost row of a '1'
        # d = bottommost row of a '1'
        l = u = sys.maxsize       # start with very large values (so min works)
        r = d = -sys.maxsize      # start with very small values (so max works)

        # Traverse the entire grid to find bounding box of all '1's
        for i in range(n):
            for j in range(m):
                if grid[i][j] == 1:
                    l = min(l, j)   # update left boundary
                    u = min(u, i)   # update top boundary
                    r = max(r, j)   # update right boundary
                    d = max(d, i)   # update bottom boundary

        # The rectangle dimensions = (width) * (height)
        return (r - l + 1) * (d - u + 1)