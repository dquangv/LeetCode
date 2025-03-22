class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];
            adj.get(nodeA).add(nodeB);
            adj.get(nodeB).add(nodeA);
        }

        int[] visited = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                List<Integer> component = new ArrayList<>();
                int edgeCount = dfs(i, visited, adj, component);
                int nodeCount = component.size();

                if (nodeCount * (nodeCount - 1) == edgeCount)
                    count++;
            }
        }
        
        return count;
    }

    public int dfs(int node, int[] visited, ArrayList<ArrayList<Integer>> adj, List<Integer> component) {
        visited[node] = 1;
        component.add(node);
        int edgeCount = 0;
        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 0) {
                edgeCount += dfs(neighbor, visited, adj, component);
            }
            edgeCount++;
        }
        return edgeCount;
    }
}