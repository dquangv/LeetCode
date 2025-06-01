class Solution {
    public long distributeCandies(int n, int m) {
        // Bước 1: Tính tổng số bộ (a, b, c) không âm thỏa a + b + c = n
        long res = ((long)n + 2) * (n + 1) / 2;

        // Bước 2: Duyệt số lượng biến (a/b/c) vượt quá m (limit)
        for (int i = 1; i <= 3; i++) {
            long rem = n - (long)i * (m + 1);  // Tổng còn lại sau khi trừ i biến vượt m
            if (rem < 0) break;  // Nếu rem < 0 thì không còn cách phân phối

            // Số bộ (a, b, c) không âm thỏa tổng = rem
            long val = (rem + 2) * (rem + 1) / 2;

            // Nếu có 1 hoặc 2 biến vượt m → có 3 cách chọn biến đó; nếu 3 biến → chỉ 1 cách
            long c = (i < 3 ? 3 : 1);

            // Áp dụng nguyên lý bao gồm - loại trừ
            // i lẻ → trừ đi (bị đếm dư); i chẵn → cộng lại (bị trừ quá)
            res += (i % 2 != 0 ? -c * val : c * val);
        }

        return res;
    }
}
