class Solution:
    def longestSubarray(self, nums: list[int]) -> int:
        # Step 1: Find the maximum value
        max_val = max(nums)

        max_len = 1
        count = 0
        i = 0

        # Step 2: Iterate to find longest streak of max_val
        while i < len(nums):
            if nums[i] == max_val:
                while i < len(nums) and nums[i] == max_val:
                    count += 1
                    i += 1
                max_len = max(max_len, count)
                count = 0
            else:
                i += 1

        return max_len
