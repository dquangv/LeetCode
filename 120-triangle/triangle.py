class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        memo = {}

        def dp(ind, i):
            if ind == n:
                return 0
            if (ind, i) in memo:
                return memo[(ind, i)]

            val = triangle[ind][i]
            left = dp(ind + 1, i)

            if ind + 1 < n and i + 1 < len(triangle[ind + 1]):
                right = dp(ind + 1, i + 1)
            else:
                right = float("inf")

            memo[(ind, i)] = val + min(left, right)
            return memo[(ind, i)]

        return dp(0, 0)
