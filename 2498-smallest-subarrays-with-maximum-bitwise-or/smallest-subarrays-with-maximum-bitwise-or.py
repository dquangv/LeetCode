class Solution:
    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        n = len(nums)
        last_seen = [0] * 30  # To store the last seen index for each bit
        res = [1] * n  # At minimum, the subarray length is 1

        for i in range(n - 1, -1, -1):  # Traverse from right to left
            for bit in range(30):  # For each bit from 0 to 29
                if nums[i] & (1 << bit):
                    last_seen[bit] = i  # Update last seen index of this bit
                res[i] = max(res[i], last_seen[bit] - i + 1)  # Update result
        return res
