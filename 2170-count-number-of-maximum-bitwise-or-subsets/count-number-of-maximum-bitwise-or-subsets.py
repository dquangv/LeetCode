class Solution:
    def countMaxOrSubsets(self, nums: list[int]) -> int:
        max_or = 0
        for n in nums:
            max_or |= n  # Compute maximum OR possible

        def dfs(i: int, curr_or: int) -> int:
            if i == len(nums):
                return 1 if curr_or == max_or else 0
            # Include nums[i]
            take = dfs(i + 1, curr_or | nums[i])
            # Exclude nums[i]
            skip = dfs(i + 1, curr_or)
            return take + skip

        return dfs(0, 0)
