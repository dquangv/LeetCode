class Solution {

    // Union-Find with path compression:
    // This function finds the next available day >= day.
    // If the day is taken (nextDay[day] != day), recursively find the next available one.
    int search(int[] nextDay, int day) {
        if (nextDay[day] != day)
            // Path compression: flatten the structure for efficiency
            nextDay[day] = search(nextDay, nextDay[day]);
        
        return nextDay[day];
    }

    public int maxEvents(int[][] events) {
        // Sort events by their end day (greedy strategy to attend earliest finishing events first)
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        // We need to track days from 1 to the maximum end day of any event
        int maxDay = events[events.length - 1][1];

        // Union-Find setup: nextDay[d] tells us the next available day >= d
        int[] nextDay = new int[maxDay + 2]; // +2 to safely access day+1 later
        for (int d = 0; d < nextDay.length; d++)
            nextDay[d] = d;
        
        int count = 0; // Total events we can attend

        // Iterate over each event
        for (int[] evt : events) {
            int start = evt[0];
            int end = evt[1];

            // Find the earliest available day >= start
            int day = search(nextDay, start);

            // If the day is within the event's range, attend it
            if (day <= end) {
                count++; // Attend the event
                nextDay[day] = search(nextDay, day + 1); // Mark this day as taken
            }
        }

        return count;
    }
}
