class Solution:
    def minOperations(self, queries: list[list[int]]) -> int:
        ans = 0  # total operations for all queries

        # Process each query range [l, r]
        for l, r in queries:
            S = 0  # total weighted steps for all numbers in [l, r]
            dMax = 0  # maximum steps among numbers in [l, r]

            # We bucket numbers based on their binary size (bit length)
            for k in range(1, 32):  # up to 2^31 > 1e9 (safe for int range)
                low = 1 << (k - 1)  # smallest number with bit length k
                high = (1 << k) - 1  # largest number with bit length k

                if low > r:
                    break  # no numbers in [l, r] with larger bit length

                # overlap between [l, r] and [low, high]
                a = max(l, low)
                b = min(r, high)

                if a > b:
                    continue  # no overlap for this k

                cnt = b - a + 1  # number of elements in this bucket

                # Minimum steps to reduce any number with bit length k to 0
                #   - Each operation divides by 4 (two right shifts in binary)
                #   - So it takes about ceil(k/2) steps
                stepsForK = (k + 1) // 2

                # Total contribution of this bucket
                S += cnt * stepsForK

                # Track maximum single-number cost
                dMax = max(dMax, stepsForK)

            # The minimum operations needed is the maximum of:
            #   - dMax: we must at least pay for the hardest number
            #   - ceil(S / 2): since each operation handles 2 numbers
            ops = max(dMax, (S + 1) // 2)

            ans += ops  # accumulate for this query

        return ans
