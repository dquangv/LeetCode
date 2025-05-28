class Solution {
    int n, m;

    // Hàm xây dựng đồ thị từ danh sách cạnh
    private void buildAdj(int[][] edges, List<List<Integer>> adj) {
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u); // vì là đồ thị vô hướng
        }
    }

    // DFS để đếm số node reachable trong khoảng cách <= maxLen
    private int dfs(int i, int parent, int maxLen, List<List<Integer>> adj) {
        if (maxLen < 0)
            return 0;
        int count = 1; // tính cả node hiện tại
        for (int neighbor : adj.get(i)) {
            if (neighbor == parent)
                continue;
            count += dfs(neighbor, i, maxLen - 1, adj);
        }
        
        return count;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        n = edges1.length + 1;
        m = edges2.length + 1;

        List<List<Integer>> adj1 = new ArrayList<>();
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj1.add(new ArrayList<>());
        for (int i = 0; i < m; i++)
            adj2.add(new ArrayList<>());

        buildAdj(edges1, adj1);
        buildAdj(edges2, adj2);

        int cnt2 = 0;
        for (int i = 0; i < m; i++)
            cnt2 = Math.max(cnt2, dfs(i, -1, k - 1, adj2));

        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = dfs(i, -1, k, adj1) + cnt2;

        return ans;
    }
}
