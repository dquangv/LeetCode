class Solution:
    def minCost(self, basket1: List[int], basket2: List[int]) -> int:
        count = Counter()

        # Count frequency difference between basket1 and basket2
        for x in basket1:
            count[x] += 1
        for x in basket2:
            count[x] -= 1

        swaps = []
        for k in count:
            if count[k] % 2 != 0:
                return -1  # Not possible if any count is odd
            swaps.extend([k] * (abs(count[k]) // 2))

        swaps.sort()
        res = 0
        if swaps:
            small = min(count.keys())
            for i in range(len(swaps) // 2):
                res += min(swaps[i], 2 * small)
        return res
