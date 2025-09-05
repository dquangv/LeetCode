class Solution:
    def makeTheIntegerZero(self, num1: int, num2: int) -> int:
        # We try different operation counts k = 1 ... 60
        # Why 60? Because 2^i can grow very large,
        # and we only need to check up to 60 bits (safe upper bound).
        for k in range(1, 61):
            # After k operations, we subtract num2 * k from num1
            x = num1 - num2 * k

            # If remaining value x is smaller than k,
            # it’s impossible to represent x using k numbers (each ≥1)
            if x < k:
                return -1

            # Check if x can be expressed as the sum of exactly k powers of two
            # Condition: the number of set bits in x (popcount) ≤ k
            # because each set bit can be split further into multiple powers of 2.
            if bin(x).count("1") <= k:
                return k

        # If no valid k found, return -1
        return -1