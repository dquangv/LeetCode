class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];

        // Khởi tạo danh sách kề
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        // Xây dựng đồ thị và mảng bậc vào
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                queue.offer(i);

        int[][] dp = new int[n][26]; // dp[i][c]: số lần màu c xuất hiện nhiều nhất trên đường đi tới node i
        int seen = 0, ans = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            seen++;

            int c = colors.charAt(u) - 'a';
            dp[u][c]++;
            ans = Math.max(ans, dp[u][c]);

            for (int v : adj.get(u)) {
                for (int k = 0; k < 26; k++)
                    dp[v][k] = Math.max(dp[v][k], dp[u][k]);

                indegree[v]--;
                if (indegree[v] == 0)
                    queue.offer(v);
            }
        }

        return seen == n ? ans : -1; // Nếu còn đỉnh chưa duyệt => có chu trình
    }
}
