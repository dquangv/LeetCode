class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int result = 0;

        // Bước 1: Khởi tạo mảng candies, mỗi đứa trẻ ít nhất 1 viên kẹo
        int[] candies = new int[n];
        for (int i = 0; i < n; i++) 
            candies[i] = 1;

        // Bước 2: Duyệt từ trái sang phải
        // Nếu đứa trẻ hiện tại có rating cao hơn đứa trước nó,
        // thì nó phải nhận nhiều kẹo hơn đứa trước => +1 kẹo so với trước đó
        for (int i = 1; i < n; i++)
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;

        // Bước 3: Duyệt từ phải sang trái
        // Nếu đứa trẻ hiện tại có rating cao hơn đứa sau nó,
        // thì nó phải có nhiều kẹo hơn đứa sau.
        // Ta dùng Math.max để giữ lại số kẹo lớn nhất giữa hai lần duyệt
        for (int i = n - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i])
                candies[i - 1] = Math.max(candies[i] + 1, candies[i - 1]);
            // Cộng dồn số kẹo đã tính
            result += candies[i - 1];
        }

        // Thêm viên kẹo cuối cùng (candies[n - 1]) vào tổng
        return result + candies[n - 1];
    }
}
