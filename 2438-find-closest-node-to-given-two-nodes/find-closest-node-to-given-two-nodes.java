class Solution {
    // Gắn nhãn: node đã được thăm bởi node1 hoặc node2
    static final int MARK1 = 1;           // node1 đã đi qua
    static final int MARK2 = 2;           // node2 đã đi qua
    static final int MARK_BOTH = MARK1 | MARK2;  // == 3, cả hai đã đi qua

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int totalNodes = edges.length;
        byte[] state = new byte[totalNodes]; // trạng thái thăm của từng node
        int meetingNode = Integer.MAX_VALUE; // khởi tạo giá trị kết quả

        // Phase 1: tiến song song từ node1 và node2 cho đến khi gặp nhau hoặc không thể đi tiếp
        while (meetingNode == Integer.MAX_VALUE && node1 >= 0 && node2 >= 0) {
            // Xử lý node1
            if ((state[node1] & MARK1) != 0)  // nếu node1 đã đi qua node này
                node1 = -1;
            else if ((state[node1] |= MARK1) == MARK_BOTH)  // nếu cả node1 và node2 đều tới node này
                meetingNode = node1;
            else
                node1 = edges[node1];  // tiếp tục đi tiếp theo cạnh

            // Xử lý node2
            if ((state[node2] & MARK2) != 0) {  // nếu node2 đã đi qua node này
                node2 = -1;
                break;
            }

            if ((state[node2] |= MARK2) == MARK_BOTH) {  // nếu gặp node đã được node1 đi qua
                meetingNode = Math.min(meetingNode, node2);
                break;
            }

            node2 = edges[node2];
        }

        // Nếu đã tìm được node mà cả hai đi tới
        if (meetingNode != Integer.MAX_VALUE) return meetingNode;

        // Phase 2: nếu node1 còn đường đi, tiếp tục tìm xem có gặp node đã đi từ node2 không
        if (node1 >= 0) {
            while (node1 >= 0) {
                if ((state[node1] & MARK1) != 0) return -1;  // lặp vô hạn
                if ((state[node1] |= MARK1) == MARK_BOTH) return node1;
                node1 = edges[node1];
            }

            return -1;
        }

        // Phase 3: tương tự nếu node2 còn đường đi
        while (node2 >= 0) {
            if ((state[node2] & MARK2) != 0) return -1;  // lặp
            if ((state[node2] |= MARK2) == MARK_BOTH) return node2;
            
            node2 = edges[node2];
        }

        // Nếu không tìm thấy node nào thỏa mãn
        return -1;
    }
}
