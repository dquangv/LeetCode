class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;

        // Step 1: Compute the gaps between meetings.
        // gaps[i] represents the free time between meeting (i-1) and meeting i.
        // gaps[0] is the time before the first meeting.
        // gaps[n] is the time after the last meeting until event ends.
        int[] gaps = new int[n + 1];
        gaps[0] = startTime[0]; // free time before first meeting

        for (int i = 1; i < n; ++i)
            // free time between the end of meeting i-1 and start of meeting i
            gaps[i] = startTime[i] - endTime[i - 1];
        
        // Free time after last meeting until event ends
        gaps[n] = eventTime - endTime[n - 1];

        // Step 2: Use sliding window to find maximum sum of up to (k+1) gaps.
        // Because rescheduling k meetings can collapse up to k gaps.
        // So we can keep at most (k + 1) gaps intact — meaning the longest stretch of free time.

        int sz = Math.min(k + 1, gaps.length); // max size of sliding window
        long curr = 0, best = 0;

        // First window of size sz
        for (int i = 0; i < sz; ++i)
            curr += gaps[i];
        
        best = curr;

        // Slide the window over the rest of the gaps
        for (int i = sz; i < gaps.length; ++i) {
            // Subtract the oldest element in the window and add the new one
            curr += gaps[i] - gaps[i - sz];
            if (curr > best)
                best = curr;
        }

        return (int) best;
    }
}
