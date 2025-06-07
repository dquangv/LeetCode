class Solution {
    public String clearStars(String s) {
        int n = s.length();

        // Dùng mảng boolean để đánh dấu ký tự nào sẽ được giữ lại
        boolean[] keep = new boolean[n];
        Arrays.fill(keep, true);

        // PriorityQueue lưu trữ các ký tự đã xuất hiện, để tìm ký tự nhỏ nhất (theo thứ tự từ điển)
        PriorityQueue<Character> pq = new PriorityQueue<>();

        // Map ký tự → danh sách các vị trí xuất hiện của nó (để xóa đúng ký tự nhỏ nhất)
        Map<Character, Deque<Integer>> charIndices = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '*') {
                // Lấy ra ký tự nhỏ nhất hiện tại (nằm bên trái '*')
                char smallestChar = pq.poll();

                // Lấy index gần nhất của ký tự nhỏ nhất (từ bên phải sang trái)
                int indexToRemove = charIndices.get(smallestChar).removeLast();

                // Đánh dấu bỏ cả dấu '*' và ký tự nhỏ nhất
                keep[i] = false; // bỏ '*'
                keep[indexToRemove] = false; // bỏ ký tự nhỏ nhất
            } else {
                // Đưa ký tự vào PriorityQueue và lưu vị trí nó xuất hiện
                pq.offer(c);
                charIndices.putIfAbsent(c, new ArrayDeque<>());
                charIndices.get(c).add(i);
            }
        }

        // Xây dựng chuỗi kết quả chỉ với các ký tự được giữ lại
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++)
            if (keep[i])
                result.append(s.charAt(i));

        return result.toString();
    }
}
