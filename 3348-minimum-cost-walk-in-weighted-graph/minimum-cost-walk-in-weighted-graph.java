class Solution {
    private int[] parent;
    private int[] minComponentCost;

    private int findRoot(int node) {
        if (parent[node] != node) {
            parent[node] = findRoot(parent[node]);
        }
        return parent[node];
    }

    private void union(int nodeA, int nodeB, int edgeWeight) {
        int rootA = findRoot(nodeA);
        int rootB = findRoot(nodeB);

        if (rootA == rootB) {
            minComponentCost[rootA] &= edgeWeight;
            return;
        }

        parent[rootA] = rootB;

        int mergedCost = minComponentCost[rootA] & minComponentCost[rootB] & edgeWeight;
        minComponentCost[rootB] = mergedCost;
    }

    public int[] minimumCost(int n, int[][] edges, int[][] queries) {
        parent = new int[n];
        minComponentCost = new int[n];
        Arrays.fill(minComponentCost, -1);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];

            if (minComponentCost[findRoot(u)] == -1) minComponentCost[findRoot(u)] = w;
            if (minComponentCost[findRoot(v)] == -1) minComponentCost[findRoot(v)] = w;

            union(u, v, w);
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            if (start == end) {
                result[i] = 0;
            } else if (findRoot(start) != findRoot(end)) {
                result[i] = -1;
            } else {
                result[i] = minComponentCost[findRoot(start)];
            }
        }

        return result;
    }
}