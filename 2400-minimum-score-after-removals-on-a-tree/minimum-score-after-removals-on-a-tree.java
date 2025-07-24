class Solution {
    private ArrayList<Integer>[] tree; // Biểu diễn cây dưới dạng danh sách kề
    private int[] val;                 // Giá trị từng node
    private int minScore;             // Biến lưu kết quả nhỏ nhất

    public int minimumScore(int[] nums, int[][] edges) {
        val = nums;
        int nodes = nums.length;
        int len = edges.length;
        tree = new ArrayList[nodes];

        // Khởi tạo danh sách kề cho mỗi node
        for (int node = 0; node < nodes; node++)
            tree[node] = new ArrayList<>();

        // Xây dựng cây từ danh sách cạnh
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        minScore = Integer.MAX_VALUE;

        // Duyệt qua từng cạnh để xem như là "cạnh bị cắt đầu tiên"
        for (int idx = 0; idx < len; idx++) {
            int node1 = edges[idx][0];
            int node2 = edges[idx][1];

            // Cắt edge giữa node1 - node2, chia cây thành 2 phần
            int xor1 = dfs(node1, node2); // Tính tổng XOR subtree của node1 (không qua node2)
            int xor2 = dfs(node2, node1); // Tương tự cho node2

            // Bây giờ, cắt cạnh thứ 2 bên trong subtree của node1
            dfs(node1, node2, xor1, xor2);

            // Hoặc cắt cạnh thứ 2 bên trong subtree của node2
            dfs(node2, node1, xor2, xor1);
        }

        return minScore;
    }

    // Tính toán XOR của subtree gốc tại node, bỏ qua parent
    private int dfs(int node, int parent) {
        int xor = val[node]; // Bắt đầu với giá trị của node hiện tại

        for (int child : tree[node])
            if (child != parent)
                xor ^= dfs(child, node); // Gộp XOR của tất cả subtree con

        return xor;
    }

    // Tìm kiếm cạnh thứ 2 để cắt (sau khi đã cắt 1 cạnh trước đó)
    private int dfs(int parent, int node, int compXor1, int treeXor) {
        int childXor = 0;

        for (int child : tree[node]) {
            if (child != parent) {
                // Gọi đệ quy để tìm subtree con
                int currChildXor = dfs(node, child, compXor1, treeXor);

                int compXor2 = currChildXor;                   // XOR của component thứ 2
                int compXor3 = treeXor ^ compXor2;             // XOR còn lại (phần còn lại của cây)

                // Tính score = max - min
                int maxXor = Math.max(compXor1, Math.max(compXor2, compXor3));
                int minXor = Math.min(compXor1, Math.min(compXor2, compXor3));

                minScore = Math.min(minScore, maxXor - minXor); // Cập nhật min score

                childXor ^= currChildXor; // Gộp các XOR lại để trả về
            }
        }

        return childXor ^ val[node];
    }
}
