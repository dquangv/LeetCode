class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = dfs(graph, visited, i);
                int nodeCount = counts[0];
                int edgeCount = counts[1];

                if (edgeCount == nodeCount * (nodeCount - 1)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private int[] dfs(List<Integer>[] graph, boolean[] visited, int node) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;

        int nodes = 0;
        int edges = 0;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            nodes++;
            edges += graph[current].size();

            for (int neighbor : graph[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }

        return new int[] { nodes, edges };
    }
}