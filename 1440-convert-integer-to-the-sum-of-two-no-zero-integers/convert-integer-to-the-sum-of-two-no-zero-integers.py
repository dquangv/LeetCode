class Solution:
    def getNoZeroIntegers(self, n: int) -> list[int]:
        def is_no_zero(x: int) -> bool:
            return '0' not in str(x)

        for a in range(1, n):
            b = n - a
            if is_no_zero(a) and is_no_zero(b):
                return [a, b]
        return []
