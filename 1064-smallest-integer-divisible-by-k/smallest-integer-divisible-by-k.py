from itertools import accumulate as acc

class Solution:
    def smallestRepunitDivByK(self, k: int) -> int:
        return min(
            (
                i + 1
                for i, r in enumerate(
                    acc(range(1, k + 3), lambda a, b: (a * 10 + 1) % k)
                )
                if r % k == 0
            ),
            default=-1,
        )
