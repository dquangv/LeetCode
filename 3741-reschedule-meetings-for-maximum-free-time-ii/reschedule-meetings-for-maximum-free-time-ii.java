class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int lastEnd = 0;
        int n = startTime.length;
        int maxLeft = 0; // Tracks the max gap on the left side of a meeting
        int res = 0;           
        int[] gaps = new int[n + 1]; // Gaps between meetings (including before first and after last)

        for (int i = 0; i < startTime.length; i++) {
            gaps[i] = startTime[i] - lastEnd; // Time between previous meeting end and current meeting start
            lastEnd = endTime[i]; // Update last end time
        }

        // Add the gap from last meeting end to the end of the event
        gaps[n] = eventTime - lastEnd;

        // Compute maxRight[i] = max gap value in gaps[i+1..n]
        int[] maxRight = new int[n + 1];
        for (int i = n - 1; i >= 0; i--)
            maxRight[i] = Math.max(maxRight[i + 1], gaps[i + 1]);

        // Iterate through each meeting, checking if moving it increases free time
        for (int i = 1; i <= n; i++) {
            int dur = endTime[i - 1] - startTime[i - 1]; // Duration of current meeting

            // Check if we can shift the meeting into a left or right gap
            if (maxLeft >= dur || maxRight[i] >= dur)
                // Try placing the current meeting between gaps[i-1] and gaps[i]
                res = Math.max(res, gaps[i - 1] + dur + gaps[i]);

            // Try just combining two adjacent gaps without moving any meeting
            res = Math.max(res, gaps[i - 1] + gaps[i]);

            // Update the maxLeft (largest gap from gaps[0] to gaps[i-1])
            maxLeft = Math.max(maxLeft, gaps[i - 1]);
        }

        return res;
    }
}
