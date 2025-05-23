class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long base = 0;// Tổng ban đầu của nums
        long sumPos = 0;// Tổng phần tăng thêm từ những node có lợi (delta > 0)
        int cntPos = 0;// Đếm số node mà XOR với k sẽ tăng giá trị
        long minPos = Long.MAX_VALUE;// Lưu node có delta dương nhỏ nhất (nếu cần bỏ đi 1 node)
        long bestNonpos = Long.MIN_VALUE;// Lưu node có delta âm ít tiêu cực nhất (nếu cần dùng bù)
        boolean sawNonpos = false;// Cờ để xem có tồn tại node có delta <= 0 không

        // Duyệt qua từng node
        for (int x : nums) {
            base += x;// Cộng tổng hiện tại
            long d = (long)(x ^ k) - x;// Tính hiệu số sau khi XOR k

            if (d > 0) {
                cntPos++;// Đếm node có lợi
                sumPos += d;// Cộng phần tăng
                minPos = Math.min(minPos, d);// Lưu delta nhỏ nhất
            } else {
                // Lưu delta âm ít tiêu cực nhất
                if (!sawNonpos || d > bestNonpos) {
                    bestNonpos = d;
                    sawNonpos = true;
                }
            }
        }

        // Nếu số node có lợi là chẵn thì áp dụng hết
        if ((cntPos & 1) == 0)
            return base + sumPos;
        

        // Nếu lẻ: phải bỏ đi 1 node có lợi, hoặc thay bằng node không có lợi
        long loss = minPos;
        if (sawNonpos) 
            loss = Math.min(loss, -bestNonpos);
        
        return base + sumPos - loss;
    }
}
