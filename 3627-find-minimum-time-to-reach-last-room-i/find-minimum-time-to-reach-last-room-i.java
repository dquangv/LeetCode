class Solution {
    // Mảng `d` để hỗ trợ duyệt 4 hướng (trái, phải, lên, xuống)
    static final int[] d = {0, 1, 0, -1, 0};

    // Lớp đại diện cho mỗi ô (cell) trong mê cung, chứa thời gian đến được ô đó và tọa độ (i, j)
    static class Cell implements Comparable<Cell> {
        int time, i, j;

        // Constructor
        Cell(int time, int i, int j) {
            this.time = time;
            this.i = i;
            this.j = j;
        }

        // Sắp xếp các ô theo thời gian tăng dần (để dùng trong PriorityQueue)
        public int compareTo(Cell other) {
            return Integer.compare(this.time, other.time);
        }
    }

    // Hàm chính để tìm thời gian tối thiểu đến đích (n-1, m-1)
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;

        // Khởi tạo mảng lưu thời gian nhỏ nhất để đến từng ô, ban đầu gán là vô cực
        int[][] time = new int[n][m];
        for (int[] row : time) Arrays.fill(row, Integer.MAX_VALUE);

        // PriorityQueue (min-heap) để chọn ô có thời gian nhỏ nhất tiếp theo
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        // Bắt đầu từ ô (0,0) với thời gian 0
        pq.offer(new Cell(0, 0, 0));
        time[0][0] = 0;

        // Bắt đầu duyệt theo thuật toán Dijkstra
        while (!pq.isEmpty()) {
            Cell cur = pq.poll(); // Lấy ô có thời gian nhỏ nhất ra

            // Nếu đã tới đích thì trả về thời gian
            if (cur.i == n - 1 && cur.j == m - 1) {
                return cur.time;
            }

            // Duyệt 4 hướng từ ô hiện tại
            for (int k = 0; k < 4; k++) {
                int ni = cur.i + d[k];     // Hàng kế bên
                int nj = cur.j + d[k + 1]; // Cột kế bên

                // Nếu nằm ngoài biên thì bỏ qua
                if (ni < 0 || nj < 0 || ni >= n || nj >= m) continue;

                // Nếu đến ô (ni, nj) sớm hơn moveTime[ni][nj], thì phải đợi đến đúng thời điểm mới được bước vào
                // Sau đó mất thêm 1 giây để đi
                int waitTime = Math.max(cur.time, moveTime[ni][nj]) + 1;

                // Nếu thời gian mới tốt hơn thời gian đã lưu trước đó
                if (waitTime < time[ni][nj]) {
                    time[ni][nj] = waitTime;        // Cập nhật thời gian mới tốt hơn
                    pq.offer(new Cell(waitTime, ni, nj)); // Đưa ô này vào hàng chờ
                }
            }
        }

        // Không thể đến đích (theo đề bài thì không xảy ra), trả -1 nếu xảy ra
        return -1;
    }
}