class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int[] placed = new int[baskets.length]; // 1 nếu giỏ đã chứa trái cây, 0 nếu trống
        
        // Duyệt qua từng trái cây
        for (int i = 0; i < fruits.length; i++) {
            boolean placedFruit = false; // Kiểm tra xem trái cây này có được đặt không
            
            // Duyệt qua từng giỏ
            for (int j = 0; j < baskets.length; j++) 
                if (placed[j] == 0 && fruits[i] <= baskets[j]) { // Giỏ trống và đủ lớn
                    placed[j] = 1; // Đánh dấu giỏ đã chứa trái cây
                    placedFruit = true; // Trái cây đã được đặt
                    break; // Thoát vòng lặp, chuyển sang trái cây tiếp theo
                }
        }
        
        // Đếm số giỏ đã được sử dụng
        int placedCount = 0;
        for (int j = 0; j < placed.length; j++)
            placedCount += placed[j];
        
        // Số trái cây không đặt được = tổng số trái cây - số giỏ đã sử dụng
        return fruits.length - placedCount;
    }
}