class Solution {
    // 4 possible directions: right, down, left, up
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private int m; // number of rows
    private int n; // number of columns

    public int minTimeToReach(int[][] moveTime) {
        m = moveTime.length;
        n = moveTime[0].length;
        return dijkstra(moveTime);
    }

    private int dijkstra(int[][] moveTime) {
        // Priority queue based on the current time (min-heap)
        Queue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Each element is {position index, current time, odd/even state}
        heap.offer(new int[] { 0, 0, 1 }); // Start from (0,0) with time 0 and odd (1-step move)
        moveTime[0][0] = -1; // Mark start cell as visited

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int r = curr[0] / n; // row index
            int c = curr[0] % n; // column index
            int time = curr[1];
            int odd = curr[2]; // odd = 1 means next move takes 1 time unit, even = 2

            for (int[] dir : DIRS) {
                int rr = r + dir[0];
                int cc = c + dir[1];
                int nextNode = rr * n + cc; // flatten index for position

                if (!isValid(rr, cc) || moveTime[rr][cc] == -1) {
                    continue; // Skip out-of-bound or already visited cells
                }

                int newTime = Math.max(time, moveTime[rr][cc]) + odd; // wait if needed
                if (rr == m - 1 && cc == n - 1) {
                    return newTime; // Reached target cell
                }

                heap.offer(new int[] { nextNode, newTime, 3 - odd }); // toggle between 1 and 2
                moveTime[rr][cc] = -1; // Mark visited
            }
        }
        return -1; // Destination not reachable
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
}