class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Array to count how many meetings each room has handled
        int[] ans = new int[n];
        // Array to track the end time of the last meeting in each room
        long[] times = new long[n];

        // Sort meetings based on start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Process each meeting
        for (int i = 0; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            boolean assigned = false;
            int minIndex = -1;
            long earliestEnd = Long.MAX_VALUE;

            // Try to assign the meeting to an available room
            for (int j = 0; j < n; j++) {
                // Keep track of the room that will be free the earliest
                if (times[j] < earliestEnd) {
                    earliestEnd = times[j];
                    minIndex = j;
                }
                // If room j is free before meeting starts, assign it
                if (times[j] <= start) {
                    assigned = true;
                    ans[j]++;           // Increment meeting count for room j
                    times[j] = end;     // Set room's next available time
                    break;              // Done assigning
                }
            }

            // If no room was free at meeting start time, delay the meeting
            if (!assigned) {
                ans[minIndex]++;  // Assign to the room that becomes free the earliest
                // Update the time to account for delayed start but same duration
                times[minIndex] += (end - start);
            }
        }

        // Find the room with the most meetings (break ties by smallest index)
        int maxMeetings = -1;
        int resultRoom = -1;
        for (int i = 0; i < n; i++)
            if (ans[i] > maxMeetings) {
                maxMeetings = ans[i];
                resultRoom = i;
            }
        
        return resultRoom;
    }
}
