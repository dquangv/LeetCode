class Solution {
    // Modulo cho tất cả phép toán
    int mod = 1_000_000_007;

    // Mảng lưu giai thừa (memo hóa)
    static int[] f = new int[100001];

    // Hàm chính đếm số mảng tốt
    public int countGoodArrays(int n, int m, int k) {
        // Nếu chưa khởi tạo giai thừa đầu tiên thì làm
        if (f[0] == 0)
            f[0] = 1;

        // Công thức:
        // m: chọn số đầu tiên
        // pow(m-1, n-1-k): số cách để tạo ra các vị trí khác nhau
        // C(n-1, n-1-k): số cách chọn các vị trí đó
        long res = m * pow(m - 1, n - 1 - k) % mod * C(n - 1, n - 1 - k) % mod;
        
        return (int) res;
    }

    // Hàm lũy thừa nhanh: tính (a^b) % mod
    public long pow(int a, int b) {
        long res = 1;
        long base = a;
        while (b > 0) {
            if ((b & 1) == 1)         // Nếu b lẻ
                res = res * base % mod;
            base = base * base % mod; // Nhân đôi cơ số
            b /= 2;
        }
        return res;
    }

    // Hàm tổ hợp: C(a, b) = a! / (b! * (a-b)!) % mod
    public long C(int a, int b) {
        return (long) getF(a) * rev(getF(a - b)) % mod * rev(getF(b)) % mod;
    }

    // Lấy giai thừa a! (với memoization)
    public long getF(int a) {
        if (f[a] != 0)
            return f[a];
        return f[a] = (int) (getF(a - 1) * a % mod);
    }

    // Tính nghịch đảo modulo: a^(-1) ≡ a^(mod-2) mod mod
    public long rev(long a) {
        if (a == 1)
            return a;
        return mod - mod / a * rev(mod % a) % mod; // công thức đệ quy theo Euclid mở rộng
    }
}