class Solution:
    def countPartitions(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        n = len(nums)
        min_stack = []
        max_stack = []
        prefix = [0, 1]
        val = -1
        j = 0
        Window = SortedList()

        for i in range(n):
            Window.add(nums[i])

            while j <= i and Window[-1] - Window[0] > k:
                Window.remove(nums[j])
                j += 1

            val = (prefix[-1] - prefix[j] + mod) % mod
            prefix.append((val + prefix[-1]) % mod)

        return val
