class Solution {
    static class Node implements Comparable<Node> {
        long time;
        int x, y;
        int dec; // 0 or 1

        Node(long time, int x, int y, int dec) {
            this.time = time;
            this.x = x;
            this.y = y;
            this.dec = dec;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.time, other.time);
        }
    }

    public int minTimeToReach(int[][] g) {
        int n = g.length;
        int m = g[0].length;
        long INF = (long) 1e15;

        long[][] dist = new long[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], INF);
        dist[0][0] = 0;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0, 0)); // time, x, y, dec

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            long d = cur.time;
            int x = cur.x, y = cur.y, dec = cur.dec;

            if (dist[x][y] < d) continue;
            if (x == n - 1 && y == m - 1) return (int) d;

            int nextDec = dec == 0 ? 1 : 0;
            long extra = dec == 0 ? 1 : 2;

            for (int i = 0; i < 4; i++) {
                int xn = x + dx[i];
                int yn = y + dy[i];
                if (xn >= 0 && xn < n && yn >= 0 && yn < m) {
                    long nextTime = Math.max(d + extra, g[xn][yn] + extra);
                    if (nextTime < dist[xn][yn]) {
                        dist[xn][yn] = nextTime;
                        pq.offer(new Node(nextTime, xn, yn, nextDec));
                    }
                }
            }
        }

        return -1;
    }
}