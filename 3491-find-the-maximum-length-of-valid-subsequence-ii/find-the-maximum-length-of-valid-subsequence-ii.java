class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        if (k == 1)
            return n; // Nếu k = 1 thì mọi cặp (a + b) % k luôn = 0 ⇒ mọi subsequence đều hợp lệ

        int res = 2; // subsequence dài nhất phải >= 2, bắt đầu từ 2
        int[] modArr = new int[n];

        // Bước 1: Tính mảng chứa phần dư (modulo) của từng phần tử theo k
        for (int i = 0; i < n; i++)
            modArr[i] = nums[i] % k;

        // Bước 2: Duyệt qua từng giá trị target (mod k) mong muốn cho (a + b) % k
        for (int j = 0; j < k; j++) {
            int[] dp = new int[k]; // dp[m] lưu độ dài subsequence dài nhất kết thúc bằng phần dư m

            for (int i = 0; i < n; i++) {
                int mod = modArr[i]; // Lấy phần dư của phần tử hiện tại
                int pos = (j - mod + k) % k; // Tìm phần dư trước đó sao cho (mod + pos) % k == j
                dp[mod] = dp[pos] + 1; // Cập nhật độ dài subsequence mới
                res = Math.max(res, dp[mod]); // Cập nhật kết quả
            }
        }

        return res;
    }
}
