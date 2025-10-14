class Solution:
    def hasIncreasingSubarrays(self, nums: List[int], k: int) -> bool:
        a = 0
        b = k
        count = 1

        while b + 1 < len(nums):
            if count == k:
                return True

            if nums[a] < nums[a + 1] and nums[b] < nums[b + 1]:
                count +=1
            else:
                count = 1

            a +=1
            b +=1

        if count == k:
            return True

        return False