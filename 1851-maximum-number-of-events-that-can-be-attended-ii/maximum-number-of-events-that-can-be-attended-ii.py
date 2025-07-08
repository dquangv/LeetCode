class Solution:
    def maxValue(self, events: list[list[int]], k: int) -> int:
        # Sort events by end time
        events.sort(key=lambda x: x[1])
        n = len(events)

        # Prepare dp table: dp[i][j] = max value using first i events and attending at most j events
        dp = [[0] * (k + 1) for _ in range(n + 1)]

        # Extract end times for binary search
        ends = [event[1] for event in events]

        for i in range(1, n + 1):
            start_i, end_i, value_i = events[i - 1]
            # Binary search to find latest event that ends before start_i
            prev = self.binarySearch(ends, start_i)
            for j in range(1, k + 1):
                # Either take or skip the current event
                dp[i][j] = max(dp[i - 1][j], dp[prev + 1][j - 1] + value_i)

        return dp[n][k]

    def binarySearch(self, ends: list[int], target: int) -> int:
        # Find the last index where end < target
        left, right = 0, len(ends) - 1
        result = -1
        while left <= right:
            mid = (left + right) // 2
            if ends[mid] < target:
                result = mid
                left = mid + 1
            else:
                right = mid - 1
        return result