class Solution {
    // Đếm số lượng node ở level chẵn/lẻ cho 2 cây A và B
    int evenA, oddA, evenB, oddB;

    // Hàm tạo danh sách kề (adjacency list) từ danh sách cạnh
    List<List<Integer>> buildList(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        return adj;
    }

    // Hàm DFS để tô màu xen kẽ chẵn/lẻ và đếm số lượng
    void dfsColor(List<List<Integer>> adj, int u, int parent, int[] color, boolean isA) {
        if (color[u] == 0) { // màu 0 → node ở level chẵn
            if (isA) evenA++; // nếu là cây A, tăng evenA
            else evenB++;     // nếu là cây B, tăng evenB
        } else {             // màu 1 → node ở level lẻ
            if (isA) oddA++;
            else oddB++;
        }

        // Duyệt tất cả các node con của u (trừ cha)
        for (int v : adj.get(u)) if (v != parent) {
            color[v] = color[u] ^ 1; // tô màu xen kẽ: 0 -> 1, 1 -> 0
            dfsColor(adj, v, u, color, isA);
        }
    }

    // Hàm chính để tính kết quả
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        // Tạo cây từ edges
        List<List<Integer>> adjA = buildList(edges1), adjB = buildList(edges2);
        int n = adjA.size(), m = adjB.size();

        // colorA và colorB dùng để lưu màu (0 hoặc 1), ban đầu gán -1
        int[] colorA = new int[n], colorB = new int[m];
        Arrays.fill(colorA, -1);
        Arrays.fill(colorB, -1);

        // Reset bộ đếm
        evenA = oddA = evenB = oddB = 0;

        // DFS từ gốc node 0 để tô màu và đếm số node even/odd
        colorA[0] = 0;
        dfsColor(adjA, 0, -1, colorA, true);  // true nghĩa là cây A
        colorB[0] = 0;
        dfsColor(adjB, 0, -1, colorB, false); // false nghĩa là cây B

        // Chọn số lượng node tối đa trong cây B (chọn even hoặc odd)
        int maxiB = Math.max(evenB, oddB);

        // Tính kết quả cho mỗi node trong cây A:
        // Nếu node A[i] ở level chẵn thì chọn nhóm chẵn của A + nhóm tốt nhất của B
        // Nếu node A[i] ở level lẻ thì chọn nhóm lẻ của A + nhóm tốt nhất của B
        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = (colorA[i] == 0 ? evenA : oddA) + maxiB;

        return res;
    }
}
