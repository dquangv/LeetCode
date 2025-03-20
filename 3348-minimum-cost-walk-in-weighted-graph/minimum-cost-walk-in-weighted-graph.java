class Solution {
    int[] root;
    int[] treeHeight;
    int[] minPathCostAND;

    public int find(int node) {
        if (root[node] == node) {
            return node;
        }

        return root[node] = find(root[node]);
    }

    public void union(int rootU, int rootV) {
        if (treeHeight[rootU] > treeHeight[rootV]) {
            minPathCostAND[rootU] &= minPathCostAND[rootV];
            root[rootV] = rootU;
        } else if (treeHeight[rootU] < treeHeight[rootV]) {
            minPathCostAND[rootV] &= minPathCostAND[rootU];
            root[rootU] = rootV;
        } else {
            minPathCostAND[rootU] &= minPathCostAND[rootV];
            root[rootV] = rootU;
            treeHeight[rootU]++;
        }
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        root = new int[n];
        treeHeight = new int[n];
        minPathCostAND = new int[n];

        for (int i = 0; i < n; i++) {
            treeHeight[i] = 1;
            root[i] = i;
            minPathCostAND[i] = -1;
        }

        for (int i = 0; i < edges.length; i++) {
            int nodeU = edges[i][0];
            int nodeV = edges[i][1];
            int weight = edges[i][2];

            int rootU = find(nodeU);
            int rootV = find(nodeV);

            if (rootU != rootV) {
                union(rootU, rootV);
                rootU = find(nodeU);
                rootV = find(nodeV);
            }

            minPathCostAND[rootU] &= weight;
        }

        int[] result = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            int nodeU = query[i][0];
            int nodeV = query[i][1];

            int rootU = find(nodeU);
            int rootV = find(nodeV);

            if (nodeU == nodeV) {
                result[i] = 0;
            } else if (rootU != rootV) {
                result[i] = -1;
            } else {
                result[i] = minPathCostAND[rootU];
            }
        }

        return result;
    }
}
