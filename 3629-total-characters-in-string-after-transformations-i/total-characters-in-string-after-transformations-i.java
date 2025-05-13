class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int MOD = (int)1e9 + 7; // Dùng để lấy phần dư tránh tràn số
        int ans = 0;            // Biến lưu kết quả cuối cùng

        // Mảng đếm số lần xuất hiện của các ký tự 'a' đến 'z'
        long[] count = new long[26];
        for (int c : s.toCharArray())
            count[c - 'a']++;  // Tăng số lượng ký tự tương ứng

        // Với mỗi chu kỳ 26 bước, ta thực hiện một vòng biến đổi xoay tròn
        while (t >= 26) {
            long z = count[25]; // Lưu lại số lượng ký tự 'z'

            // Dịch chuyển từ 'a' → 'b', ..., 'y' → 'z'
            for (int i = 25; i > 0; i--)
                count[i] = (count[i] + count[i - 1]) % MOD;

            // Xử lý trường hợp 'z' quay lại 'a' và 'b'
            count[0] = (count[0] + z) % MOD;
            count[1] = (count[1] + z) % MOD;

            t -= 26;
        }

        // Cộng tổng tất cả các ký tự sau khi biến đổi trọn vẹn từng chu kỳ 26
        for (int i = 0; i < 26; i++)
            ans = (int)((ans + count[i]) % MOD);

        // Nếu còn dư t < 26 bước chưa xử lý, ta chỉ cộng phần ảnh hưởng
        for (int i = 26 - t; i < 26; i++)
            ans = (int)((ans + count[i]) % MOD);

        return ans;
    }
}
