import heapq

class Solution:
    def minimumDifference(self, nums: list[int]) -> int:
        n = len(nums) // 3

        max_heap = []
        min_heap = []
        left_sum = 0
        right_sum = 0
        min_left = [0] * (3 * n)

        # max heap (invert sign for Python)
        for i in range(n):
            left_sum += nums[i]
            heapq.heappush(max_heap, -nums[i])
        min_left[n - 1] = left_sum

        for i in range(n, 2 * n):
            left_sum += nums[i]
            heapq.heappush(max_heap, -nums[i])
            left_sum += heapq.heappop(max_heap)  # remove the largest (- smallest)
            min_left[i] = left_sum

        # min heap
        for i in range(3 * n - 1, 2 * n - 1, -1):
            right_sum += nums[i]
            heapq.heappush(min_heap, nums[i])

        res = min_left[2 * n - 1] - right_sum

        for i in range(2 * n - 1, n - 1, -1):
            right_sum += nums[i]
            heapq.heappush(min_heap, nums[i])
            right_sum -= heapq.heappop(min_heap)
            res = min(res, min_left[i - 1] - right_sum)

        return res
