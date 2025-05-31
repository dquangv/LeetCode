class Solution {
    public int snakesAndLadders(int[][] board) {
        int size = board.length; // Kích thước bảng (n)
        int target = size * size; // Vị trí đích là n^2

        // Bước 1: Chuyển bảng 2D thành mảng 1 chiều theo thứ tự Boustrophedon
        short[] flattened = new short[target + 1]; // flattened[1..n^2]
        int index = 1;

        for (int row = size - 1; row >= 0; row--) {
            // Nếu dòng này đi từ trái sang phải
            for (int col = 0; col < size; col++)
                flattened[index++] = (short) board[row][col];

            // Kiểm tra nếu còn dòng tiếp theo không
            if (--row < 0)
                break;

            // Dòng kế tiếp đi từ phải sang trái
            for (int col = size - 1; col >= 0; col--)
                flattened[index++] = (short) board[row][col];
        }

        // Bước 2: Chuẩn bị BFS với hàng đợi vòng
        short[] queue = new short[target]; // Hàng đợi vòng để tiết kiệm bộ nhớ
        int head = 0, tail = 0;
        queue[tail++] = 1; // Bắt đầu từ ô số 1

        int[] steps = new int[target + 1]; // steps[i] là số bước cần để đến ô i
        steps[1] = 1; // Bắt đầu từ bước 1 (tức 0 lần tung xúc xắc + 1)

        // Bước 3: Thực hiện BFS
        while (head != tail) {
            int position = queue[head++];
            head %= target; // Đảm bảo head không vượt khỏi mảng

            // Nếu trong 1 nước đi tới được đích thì trả về kết quả ngay
            if (position + 6 >= target)
                return steps[position];

            int maxNeutral = 0; // Lưu roll cao nhất không bị snake/ladder để tránh duplicate

            // Thử các giá trị xúc xắc từ 6 đến 1
            for (int roll = 6; roll >= 1; roll--) {
                int next = position + roll;

                if (flattened[next] >= 0) {
                    // Có snake/ladder => buộc phải nhảy đến đích của nó
                    next = flattened[next];
                    if (next == target)
                        return steps[position]; // Về đích
                } else {
                    // Nếu không có snake/ladder
                    if (roll < maxNeutral)
                        continue; // Bỏ nếu không phải roll cao nhất
                    maxNeutral = roll;
                }

                // Nếu ô này chưa từng được đến thì thêm vào hàng đợi
                if (steps[next] == 0) {
                    steps[next] = steps[position] + 1;
                    queue[tail++] = (short) next;
                    tail %= target;

                    // Nếu hàng đợi bị tràn (circular queue đầy) => lỗi logic
                    if (head == tail)
                        return 0;
                }
            }
        }

        // Không thể đến ô cuối cùng
        return -1;
    }
}
