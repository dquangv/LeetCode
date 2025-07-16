class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        n = len(nums)

        if n == 2:
            return 2

        z = nums[0] % 2 == 1  # parity of first number

        length = [0, 0, 0]
        length[1 if z else 0] = 1
        length[2] = 1

        for i in range(1, n):
            x = nums[i] % 2 == 1  # parity of current number

            length[1 if x else 0] += 1

            if x != z:
                length[2] += 1
                z = not z

        return max(length[0], length[1], length[2])
