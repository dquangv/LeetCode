class Solution:
    def maxSum(self, nums: list[int]) -> int:
        seen = set()
        total = 0
        has_non_negative = False

        for num in nums:
            if num >= 0:
                has_non_negative = True
                if num not in seen:
                    seen.add(num)
                    total += num

        if has_non_negative:
            return total
        else:
            # All numbers are negative
            return max(nums)
