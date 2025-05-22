class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;
        int qz = queries.length;

        // Lưu danh sách các truy vấn kết thúc tại chỉ số nào
        List<Integer>[] qEnd = new ArrayList[n];
        for (int i = 0; i < n; i++) qEnd[i] = new ArrayList<>();

        for (int[] query : queries) {
            int l = query[0], r = query[1];
            qEnd[l].add(r);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        int[] cntQ = new int[n + 1]; // số lần giảm đang hoạt động tại mỗi vị trí
        int dec = 0;

        for (int i = 0; i < n; i++) {
            dec += cntQ[i];

            // Thêm các truy vấn bắt đầu tại i vào heap
            for (int r : qEnd[i]) {
                pq.offer(r);
            }

            int x = nums[i];
            // Dùng các truy vấn trong heap để giảm nums[i] về 0
            while (x > dec && !pq.isEmpty() && pq.peek() >= i) {
                int k = pq.poll();
                cntQ[k + 1]--; // giảm lại tại điểm kết thúc + 1
                dec++;
            }

            if (x > dec) return -1; // Không thể giảm về 0
        }

        return pq.size(); // số truy vấn còn lại chưa dùng
    }
}