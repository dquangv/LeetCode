class Solution:
    def productQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        MOD = 10**9 + 7
        powers = []

        for i in range(32):
            if (n & (1 << i)) != 0:
                powers.append(1 << i)

        ans = []

        for left, right in queries:
            product = powers[left]

            for i in range(left + 1, right + 1):
                product = (product * powers[i]) % MOD

            ans.append(product)

        return ans
