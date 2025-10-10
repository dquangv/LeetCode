class Solution:
    def maximumEnergy(self, energy: List[int], k: int) -> int:
        memo = [0 for _ in range(k)]
        for x in energy:
            memo.append(max(x + memo[-k], x))
            
        return max(memo[-k:])
