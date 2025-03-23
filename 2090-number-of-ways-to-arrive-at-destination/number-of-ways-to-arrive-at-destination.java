class NodeTime implements Comparable<NodeTime> {
    int node;
    long time;

    public NodeTime(int node, long time) {
        this.node = node;
        this.time = time;
    }

    @Override
    public int compareTo(NodeTime other) {
        return Long.compare(this.time, other.time);
    }
}

class Solution {
    public int countPaths(int n, int[][] roads) {
        int numEdges = roads.length;
        ArrayList<ArrayList<NodeTime>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < numEdges; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int time = roads[i][2];

            adj.get(u).add(new NodeTime(v, time));
            adj.get(v).add(new NodeTime(u, time));
        }

        long[] minDist = new long[n];
        int[] pathCount = new int[n];

        Arrays.fill(minDist, (long) 1e18);
        minDist[0] = 0;
        pathCount[0] = 1;

        PriorityQueue<NodeTime> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new NodeTime(0, 0));

        int MOD = (int) (1e9 + 7);

        while (!priorityQueue.isEmpty()) {
            NodeTime current = priorityQueue.poll();
            int currentNode = current.node;
            long currentTime = current.time;

            if (currentTime > minDist[currentNode])
                continue;

            for (NodeTime neighbor : adj.get(currentNode)) {
                int nextNode = neighbor.node;
                long edgeTime = neighbor.time;

                if (minDist[nextNode] > currentTime + edgeTime) {
                    minDist[nextNode] = currentTime + edgeTime;
                    priorityQueue.add(new NodeTime(nextNode, minDist[nextNode]));
                    pathCount[nextNode] = pathCount[currentNode];
                } else if (minDist[nextNode] == currentTime + edgeTime) {
                    pathCount[nextNode] = (pathCount[nextNode] + pathCount[currentNode]) % MOD;
                }
            }
        }

        return pathCount[n - 1];
    }
}