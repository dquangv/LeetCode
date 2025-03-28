class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int k = queries.length;
        int[] result = new int[k];

        int[][] queryIndex = new int[k][2];
        for (int i = 0; i < k; i++) {
            queryIndex[i][0] = queries[i];
            queryIndex[i][1] = i;
        }
        Arrays.sort(queryIndex, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int count = 0;
        int index = 0;

        while (index < k) {
            int queryValue = queryIndex[index][0];
            int queryIdx = queryIndex[index][1];

            while (!pq.isEmpty() && pq.peek()[0] < queryValue) {
                int[] cell = pq.poll();
                int val = cell[0], r = cell[1], c = cell[2];

                count++;

                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        pq.offer(new int[]{grid[nr][nc], nr, nc});
                    }
                }
            }

            result[queryIdx] = count;
            index++;
        }

        return result;
    }
}