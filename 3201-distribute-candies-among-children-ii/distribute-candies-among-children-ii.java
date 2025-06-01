class Solution {
    public long distributeCandies(int n, int limit) {
        long res = 0;
        // Duyệt số kẹo cho đứa đầu tiên (i từ 0 đến min(limit, n))
        for (int i = 0; i <= Math.min(limit, n); i++) 
            // Tổng còn lại cho 2 đứa còn lại là n - i
            // Nếu phần còn lại <= 2 * limit thì có thể phân phối được
            if (n - i <= 2 * limit)
                // Đếm số bộ (j, k) thỏa j + k = n - i, với 0 <= j, k <= limit
                // Số lượng cách = min(n - i, limit) - max(0, n - i - limit) + 1
                res += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        
        return res;
    }
}
