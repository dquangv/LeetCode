class Solution:
    def magicalSum(self, M: int, K: int, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        B = n + max(M.bit_length(), 1)
        f = [1] * (M + 1)
        for i in range(1, M + 1):
            f[i] = f[i - 1] * i % mod
        invf = [1] * (M + 1)
        invf[M] = pow(f[M], mod - 2, mod)
        for i in range(M, 0, -1):
            invf[i - 1] = invf[i] * i % mod
        pw = [[1] * (M + 1) for _ in range(n)]
        for i in range(n):
            for c in range(1, M + 1):
                pw[i][c] = pw[i][c - 1] * nums[i] % mod
        dp = [[[0] * (M + 1) for _ in range(K + 1)] for __ in range(M + 1)]
        dp[0][0][0] = 1
        for j in range(B):
            ndp = [[[0] * (M + 1) for _ in range(K + 1)] for __ in range(M + 1)]
            if j < n:
                for carry in range(M + 1):
                    for ones in range(K + 1):
                        for s in range(M + 1):
                            v = dp[carry][ones][s]
                            if not v:
                                continue
                            for c in range(M - s + 1):
                                nc = s + c
                                sj = c + carry
                                b = sj & 1
                                ncarr = sj >> 1
                                no = ones + b
                                if no > K:
                                    continue
                                val = v * invf[c] % mod * pw[j][c] % mod
                                ndp[ncarr][no][nc] = (ndp[ncarr][no][nc] + val) % mod
            else:
                for carry in range(M + 1):
                    for ones in range(K + 1):
                        for s in range(M + 1):
                            v = dp[carry][ones][s]
                            if not v:
                                continue
                            sj = carry
                            b = sj & 1
                            ncarr = sj >> 1
                            no = ones + b
                            if no > K:
                                continue
                            ndp[ncarr][no][s] = (ndp[ncarr][no][s] + v) % mod
            dp = ndp
        return dp[0][K][M] * f[M] % mod
