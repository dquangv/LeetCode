# Python 3 version of the Java solution
class Solution:
    def earliestAndLatest(self, n: int, firstPlayer: int, secondPlayer: int) -> [int]:
        from functools import lru_cache

        @lru_cache(None)
        def dp(n, left, right):
            if left + right == n + 1:
                return [1, 1]
            if n == 3 or n == 4:
                return [2, 2]

            if left - 1 > n - right:
                temp = n + 1 - left
                left = n + 1 - right
                right = temp

            nextRound = (n + 1) // 2
            minRound, maxRound = n, 1

            if right * 2 <= n + 1:
                preLeft = left - 1
                midGap = right - left - 1
                for i in range(preLeft + 1):
                    for j in range(midGap + 1):
                        res = dp(nextRound, i + 1, i + j + 2)
                        minRound = min(minRound, 1 + res[0])
                        maxRound = max(maxRound, 1 + res[1])
            else:
                mirrored = n + 1 - right
                preLeft = left - 1
                midGap = mirrored - left - 1
                innerGap = right - mirrored - 1
                for i in range(preLeft + 1):
                    for j in range(midGap + 1):
                        res = dp(nextRound, i + 1, i + j + 1 + (innerGap + 1) // 2 + 1)
                        minRound = min(minRound, 1 + res[0])
                        maxRound = max(maxRound, 1 + res[1])

            return [minRound, maxRound]

        return dp(n, min(firstPlayer, secondPlayer), max(firstPlayer, secondPlayer))
