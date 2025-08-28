class Solution:
    def sortMatrix(self, grid: List[List[int]]) -> List[List[int]]:
        n = len(grid)
        diagonals = {}

        # Step 1: Collect numbers by diagonals (key = i - j)
        for i in range(n):
            for j in range(n):
                key = i - j
                if key not in diagonals:
                    diagonals[key] = []
                diagonals[key].append(grid[i][j])

        # Step 2: Sort each diagonal
        for key, diag in diagonals.items():
            if key >= 0:  # bottom-left (including main diagonal)
                diag.sort(reverse=True)  # descending
            else:  # top-right
                diag.sort()  # ascending

        # Step 3: Place back into grid
        for i in range(n):
            for j in range(n):
                key = i - j
                grid[i][j] = diagonals[key].pop(0)

        return grid
