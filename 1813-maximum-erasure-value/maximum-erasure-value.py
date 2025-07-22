class Solution:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        seen = set()
        current_sum = result = 0
        start = 0

        for end in range(len(nums)):
            while nums[end] in seen:
                seen.remove(nums[start])
                current_sum -= nums[start]
                start += 1

            current_sum += nums[end]
            seen.add(nums[end])
            result = max(result, current_sum)

        return result