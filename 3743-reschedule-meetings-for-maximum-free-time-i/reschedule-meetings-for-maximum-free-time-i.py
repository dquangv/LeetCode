class Solution(object):
    def maxFreeTime(self, eventTime, k, startTime, endTime):
        """
        :type eventTime: int
        :type k: int
        :type startTime: List[int]
        :type endTime: List[int]
        :rtype: int
        """
        n = len(startTime)
        gaps = [0] * (n + 1)

        # gaps[0]: time before the first meeting
        gaps[0] = startTime[0]

        # gaps[i]: time between end of meeting i-1 and start of meeting i
        for i in range(1, n):
            gaps[i] = startTime[i] - endTime[i - 1]

        # gaps[n]: time after last meeting until the event ends
        gaps[n] = eventTime - endTime[-1]

        sz = min(k + 1, len(gaps))

        # Use sliding window to find the max sum of sz consecutive gaps
        curr = sum(gaps[:sz])
        best = curr

        for i in range(sz, len(gaps)):
            curr += gaps[i] - gaps[i - sz]
            best = max(best, curr)

        return best