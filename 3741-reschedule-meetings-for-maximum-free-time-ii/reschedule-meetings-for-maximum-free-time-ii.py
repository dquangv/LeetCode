class Solution:
    def maxFreeTime(self, eventTime: int, startTime: list[int], endTime: list[int]) -> int:
        n = len(startTime)
        lastEnd = 0
        maxLeft = 0
        res = 0

        gaps = [0] * (n + 1)
        for i in range(n):
            gaps[i] = startTime[i] - lastEnd
            lastEnd = endTime[i]
        gaps[n] = eventTime - lastEnd

        maxRight = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            maxRight[i] = max(maxRight[i + 1], gaps[i + 1])

        for i in range(1, n + 1):
            dur = endTime[i - 1] - startTime[i - 1]
            if maxLeft >= dur or maxRight[i] >= dur:
                res = max(res, gaps[i - 1] + dur + gaps[i])
            res = max(res, gaps[i - 1] + gaps[i])
            maxLeft = max(maxLeft, gaps[i - 1])

        return res
