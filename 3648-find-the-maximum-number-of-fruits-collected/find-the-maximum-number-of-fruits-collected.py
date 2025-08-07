class Solution:
    def maxCollectedFruits(self, fruits: List[List[int]]) -> int:
        n = len(fruits)

        # Step 1: Mark inaccessible cells with 0
        for i in range(n):
            for j in range(n):
                if i < j and j < n - 1 - i:
                    fruits[i][j] = 0
                if j < i and i < n - 1 - j:
                    fruits[i][j] = 0

        # Step 2: First child - diagonal path
        res = sum(fruits[i][i] for i in range(n))

        # Step 3: Second child - top-right to bottom-right
        for i in range(1, n):
            for j in range(i + 1, n):
                fruits[i][j] += max(
                    fruits[i - 1][j - 1],
                    fruits[i - 1][j],
                    fruits[i - 1][j + 1] if j + 1 < n else 0
                )

        # Step 4: Third child - bottom-left to bottom-right
        for j in range(1, n):
            for i in range(j + 1, n):
                fruits[i][j] += max(
                    fruits[i - 1][j - 1],
                    fruits[i][j - 1],
                    fruits[i + 1][j - 1] if i + 1 < n else 0
                )

        return res + fruits[n - 1][n - 2] + fruits[n - 2][n - 1]