class Solution:
    def triangularSum(self, nums: List[int]) -> int:
        res, nCr, n = 0, 1, len(nums) - 1

        for r, num in enumerate(nums):
            res = (res + num * nCr) % 10
            nCr = nCr * (n - r) // (r + 1)
            
        return res
