class Solution:
    def prefixesDivBy5(self, nums: List[int]) -> List[bool]:
        ans = []
        mod = 0

        for bit in nums:
            mod = (mod * 2 + bit) % 5
            ans.append(mod == 0)

        return ans
