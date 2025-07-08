class Solution {
    public int maxValue(int[][] events, int k) {
        // Sort events by their end day in ascending order.
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;

        // dp[i][j] will represent the maximum value we can obtain
        // by considering the first i events and attending at most j events.
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            int[] event = events[i - 1]; // event = [start, end, value]

            // Find the index of the last event that ends before the current event starts
            // This ensures no overlapping events are picked
            int prev = binarySearch(events, event[0]);

            for (int j = 1; j <= k; j++)
                // Choice 1: skip current event → dp[i-1][j]
                // Choice 2: take current event and add its value
                //          → dp[prev+1][j-1] + event[2]
                dp[i][j] = Math.max(dp[i - 1][j], dp[prev + 1][j - 1] + event[2]);
        }

        // Final answer: max value by attending up to k events from all n events
        return dp[n][k];
    }

    // Binary search to find the latest event that ends before currentStart
    private int binarySearch(int[][] events, int currentStart) {
        int left = 0, right = events.length - 1;
        int result = -1;

        // We want the event with the largest end day < currentStart
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (events[mid][1] < currentStart) {
                result = mid; // Found a valid event, try to find later one
                left = mid + 1;
            } else
                right = mid - 1; // End too late, look earlier
        }

        return result; // Return index of valid previous event or -1 if none found
    }
}
