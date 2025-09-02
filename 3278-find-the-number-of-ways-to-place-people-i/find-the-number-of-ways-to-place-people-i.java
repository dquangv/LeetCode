class Solution {
    public int numberOfPairs(int[][] points) {
        // Step 1: Sort by x ascending, and if x is equal, by y descending
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        int cnt = 0;

        // Step 2: Iterate each point A = (x, y)
        for (int i = 0; i < points.length - 1; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int lower = -1;

            // Step 3: Look at candidate B points to the right
            for (int j = i + 1; j < points.length; j++) {
                if (lower < points[j][1] && points[j][1] <= y) {
                    // ✅ Found a valid pair (A, B)
                    cnt++;
                    lower = points[j][1];
                }
                if (lower == y)
                    break;
            }
        }

        return cnt;
    }
}