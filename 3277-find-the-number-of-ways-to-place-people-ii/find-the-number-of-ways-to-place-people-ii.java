class Solution {
    public int numberOfPairs(int[][] points) {
        // Step 1: Sort by x descending, then y ascending
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(b[0], a[0]);
        });

        int n = points.length;
        int ans = 0;

        // Step 2: Iterate over Alice's position
        for (int i = 0; i < n - 1; i++) {
            int yi = points[i][1];
            int y = Integer.MAX_VALUE; // initial upper bound

            // Step 3: Iterate over Bob's position
            for (int j = i + 1; j < n; j++) {
                int yj = points[j][1];
                if (y > yj && yj >= yi) {
                    ans++;
                    y = yj;
                    if (yi == yj)
                        break;
                }
            }
        }

        return ans;
    }
}