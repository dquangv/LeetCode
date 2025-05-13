class Solution {
    static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t) {
        int[][] dp = new int[t + 1][26];

        for (int i = 0; i < 26; i++)
            dp[0][i] = 1;

        for (int step = 1; step <= t; step++) {
            for (int i = 0; i < 26; i++) {
                if (i < 25)
                    dp[step][i] = dp[step - 1][i + 1];
                else
                    dp[step][i] = (dp[step - 1][0] + dp[step - 1][1]) % MOD;

            }
        }

        long total = 0;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            total = (total + dp[t][idx]) % MOD;
        }

        return (int) total;
    }
}
