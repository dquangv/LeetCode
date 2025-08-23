class Solution:
    def minimumSum(self, grid: List[List[int]]) -> int:
        from collections import defaultdict
        memo = defaultdict(int)  # memoization cache

        # Function: find bounding rectangle area of all 1's inside subgrid
        def get_one(i1, j1, i2, j2):
            minx = float('inf')
            maxx = float('-inf')
            miny = float('inf')
            maxy = float('-inf')

            # Scan subgrid
            for i in range(i1, i2 + 1):
                for j in range(j1, j2 + 1):
                    if grid[i][j] == 1:
                        minx = min(minx, i)
                        maxx = max(maxx, i)
                        miny = min(miny, j)
                        maxy = max(maxy, j)

            # If no '1' inside subgrid
            if minx == float('inf'):
                return 0

            # Return area of bounding rectangle
            return (maxx - minx + 1) * (maxy - miny + 1)

        # Recursive function: minimum sum of areas using k rectangles
        def get_next(i1, j1, i2, j2, k):
            key = (i1, j1, i2, j2, k)
            if key in memo:
                return memo[key]

            output = float('inf')

            if k == 1:  # just one rectangle
                output = get_one(i1, j1, i2, j2)

            elif k == 2:  # split into two rectangles
                # Horizontal splits
                for i in range(i1, i2):
                    output = min(output,
                                 get_next(i1, j1, i, j2, 1) + get_next(i + 1, j1, i2, j2, 1))
                # Vertical splits
                for j in range(j1, j2):
                    output = min(output,
                                 get_next(i1, j1, i2, j, 1) + get_next(i1, j + 1, i2, j2, 1))

            elif k == 3:  # split into three rectangles
                # Horizontal first
                for i in range(i1, i2):
                    # 1 + 2 split
                    output = min(output,
                                 get_next(i1, j1, i, j2, 1) + get_next(i + 1, j1, i2, j2, 2))
                    # 2 + 1 split
                    output = min(output,
                                 get_next(i1, j1, i, j2, 2) + get_next(i + 1, j1, i2, j2, 1))
                # Vertical splits
                for j in range(j1, j2):
                    output = min(output,
                                 get_next(i1, j1, i2, j, 1) + get_next(i1, j + 1, i2, j2, 2))
                    output = min(output,
                                 get_next(i1, j1, i2, j, 2) + get_next(i1, j + 1, i2, j2, 1))

            memo[key] = output
            return output

        m, n = len(grid), len(grid[0])
        # Start with whole grid, need 3 rectangles
        return get_next(0, 0, m - 1, n - 1, 3)
