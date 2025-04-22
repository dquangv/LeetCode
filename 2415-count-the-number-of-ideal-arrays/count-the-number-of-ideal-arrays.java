class Solution {
    static final int MOD = 1_000_000_007;
    int[][] comb; // combination table

    public int idealArrays(int n, int maxValue) {
        // max length of sequences starting with a number can't exceed log2(maxValue)
        int maxLen = 14; // Because 2^14 > 10^4

        // Step 1: Tính tổ hợp C[n][k] để chọn vị trí
        comb = new int[n + 1][maxLen + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= maxLen && j <= i; j++) {
                comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
            }
        }

        // Step 2: dp[length][value]: số chuỗi bắt đầu từ value có độ dài length
        int[][] dp = new int[maxLen + 1][maxValue + 1];
        for (int i = 1; i <= maxValue; i++)
            dp[1][i] = 1;

        for (int len = 2; len <= maxLen; len++) {
            for (int i = 1; i <= maxValue; i++) {
                for (int mult = 2 * i; mult <= maxValue; mult += i) {
                    dp[len][mult] = (dp[len][mult] + dp[len - 1][i]) % MOD;
                }
            }
        }

        // Step 3: Tính tổng tất cả các cách
        long res = 0;
        for (int len = 1; len <= maxLen; len++) {
            for (int i = 1; i <= maxValue; i++) {
                res = (res + ((long) dp[len][i] * comb[n - 1][len - 1]) % MOD) % MOD;
            }
        }
        
        return (int) res;
    }
}