class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums); // Bước 1: Sắp xếp mảng để các phần tử gần nhau có hiệu nhỏ nhất
        return binarySearch(nums, p); // Dùng binary search để tìm giá trị hiệu lớn nhất nhỏ nhất có thể
    }

    // Hàm kiểm tra nếu ta chọn cặp sao cho hiệu <= d thì có đủ p cặp hay không
    private boolean compute(int[] nums, int d, int p) {
        int c = 0; // Biến đếm số cặp đã chọn được
        for (int i = 0; i < nums.length - 1; i++) {
            // Nếu hiệu giữa nums[i+1] và nums[i] <= d thì ghép thành cặp
            if (nums[i + 1] - nums[i] <= d) {
                c++; // Đếm thêm một cặp
                i++; // Bỏ qua phần tử i+1 để tránh trùng lặp
            }
            if (c >= p)
                return true; // Đã đủ p cặp rồi
        }
        return false; // Không đủ p cặp
    }

    // Binary search để tìm giá trị hiệu lớn nhất nhỏ nhất có thể
    private int binarySearch(int[] nums, int p) {
        int L = 0; // Giá trị nhỏ nhất có thể
        int H = nums[nums.length - 1] - nums[0]; // Giá trị hiệu lớn nhất có thể

        // Dùng binary search để tìm min của max
        while (L < H) {
            int M = L + ((H - L) >> 1); // Lấy giá trị giữa

            if (compute(nums, M, p))
                // Nếu có thể chọn đủ p cặp với hiệu <= M thì thu hẹp biên phải
                H = M;
            else
                // Nếu không đủ cặp thì cần tăng hiệu lên
                L = M + 1;
        }

        // Khi L == H thì đó là giá trị nhỏ nhất thỏa mãn
        return L;
    }
}
