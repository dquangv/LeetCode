class Solution {
    static final int MOD = (int) 1e9 + 7;

    public int possibleStringCount(String word, int k) {
        int n = word.length();
        if (n < k)
            return 0; // Không thể có chuỗi gốc nào nếu độ dài nhỏ hơn k
        if (n == k)
            return 1; // Nếu bằng nhau, chỉ có 1 khả năng là chính chuỗi đó

        // Xử lý các đoạn ký tự giống nhau liên tiếp
        List<Integer> seg = new ArrayList<>();
        seg.add(1);
        for (int i = 1; i < n; i++)
            if (word.charAt(i) == word.charAt(i - 1))
                seg.set(seg.size() - 1, seg.get(seg.size() - 1) + 1);
            else
                seg.add(1);

        int m = seg.size();

        // Tính tổng số chuỗi gốc có thể, mỗi đoạn có ít nhất 1 ký tự
        long total = 1;
        boolean takeMod = false;
        for (int x : seg) {
            total *= x;
            if (total >= MOD) {
                total %= MOD;
                takeMod = true;
            }
        }

        if (total == 1 && !takeMod)
            return 1; // Trường hợp đặc biệt: không có đoạn nào bị lặp

        if (k <= m)
            return (int) total; // Nếu k <= số đoạn thì mọi chuỗi gốc đều hợp lệ

        // Đếm số chuỗi gốc có độ dài nhỏ hơn k (invalid strings)
        // Ta cần đếm số cách phân bố số lần lặp lại sao cho tổng z0 + z1 + ... + z[m-1] <= k - m - 1
        // với 0 <= zi <= seg[i] - 1
        int maxT = k - m - 1;

        long[][] dp = new long[2][maxT + 1];
        long[] prefix = new long[maxT + 2]; // prefix[i+1] = tổng dp[j][0..i]
        dp[0][0] = 1; // cơ sở: chỉ có 1 cách để tạo độ dài 0

        for (int j = 0; j < m; j++) {
            int s = seg.get(j);
            // Tính prefix sum cho dp[j % 2]
            prefix[0] = 0;
            for (int i = 0; i <= maxT; i++)
                prefix[i + 1] = (prefix[i] + dp[j % 2][i]) % MOD;

            // Tính dp[(j + 1) % 2][i] sử dụng sliding window prefix
            for (int i = 0; i <= maxT; i++) {
                int L = Math.max(0, i - (s - 1));
                int R = i;
                dp[(j + 1) % 2][i] = (prefix[R + 1] - prefix[L] + MOD) % MOD;
            }
        }

        // Tổng số chuỗi gốc có độ dài < k
        long lessK = 0;
        for (int i = 0; i <= maxT; i++)
            lessK = (lessK + dp[m % 2][i]) % MOD;

        // Trả về total - lessK (modulo)
        return (int) ((total - lessK + MOD) % MOD);
    }
}
