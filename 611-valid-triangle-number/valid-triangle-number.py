class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        nums.sort()
        count = 0
        n = len(nums)

        for c in range(n - 1, 1, -1):
            a, b = 0, c - 1
            while a < b:
                if nums[a] + nums[b] > nums[c]:
                    count += b - a
                    b -= 1  # Move the upper pointer down
                else:
                    a += 1  # Move the lower pointer up

        return count
