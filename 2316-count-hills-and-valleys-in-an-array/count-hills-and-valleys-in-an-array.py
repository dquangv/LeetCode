class Solution:
    def countHillValley(self, nums: list[int]) -> int:
        count = 0
        left = 0  # Index of last non-equal value

        for i in range(1, len(nums) - 1):
            if nums[i] != nums[i + 1]:
                if (nums[i] > nums[left] and nums[i] > nums[i + 1]) or \
                   (nums[i] < nums[left] and nums[i] < nums[i + 1]):
                    count += 1
                left = i  # Update left to current index

        return count
