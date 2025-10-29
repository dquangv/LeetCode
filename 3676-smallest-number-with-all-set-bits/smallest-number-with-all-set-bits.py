class Solution:
    def smallestNumber(self, n: int) -> int:
        k = 1
        while True:
            val = (1 << k) - 1
            if val >= n:
                return val
            k += 1
