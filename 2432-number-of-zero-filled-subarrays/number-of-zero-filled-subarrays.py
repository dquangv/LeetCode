class Solution:
    def zeroFilledSubarray(self, nums: List[int]) -> int:
        count = 0  # current streak of zeros
        total = 0 

        for num in nums:
            if num == 0:
                count += 1        # extend streak
                total += count    # add all subarrays ending here
            else:
                count = 0         # reset streak

        return total