class Solution:
    def numSubseq(self, nums: List[int], target: int) -> int:
        MOD = 10**9 + 7
        nums.sort()  # Sắp xếp mảng để sử dụng 2-pointer

        n = len(nums)
        power = [1] * n
        for i in range(1, n):
            power[i] = power[i - 1] * 2 % MOD  # Tiền xử lý 2^i % MOD

        ans = 0
        left, right = 0, n - 1

        while left <= right:
            if nums[left] + nums[right] <= target:
                ans = (ans + power[right - left]) % MOD
                left += 1
            else:
                right -= 1  # Giảm max nếu vượt quá target

        return ans
