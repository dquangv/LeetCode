class Solution:
    def new21Game(self, n: int, k: int, maxPts: int) -> float:
        if k == 0 or n >= k +maxPts:
            return 1.0

        dp = [0.0] * (n + 1)
        dp[0] = 1.0

        total = 1.0
        res = 0.0

        for i in range(1, n + 1):
            dp[i] = total / maxPts

            if i < k:
                total += dp[i]
            else:
                res += dp[i]

            if i >= maxPts:
                total -= dp[i - maxPts]

        return res