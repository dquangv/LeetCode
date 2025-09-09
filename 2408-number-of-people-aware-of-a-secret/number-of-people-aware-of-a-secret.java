class Solution {
    static final int MOD = 1_000_000_007;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[] dp = new int[n + 1];
        dp[1] = 1; // day 1: one person knows the secret

        for (int t = 1; t <= n; t++) 
            // they share the secret starting from (t+delay) until (t+forget-1)
            for (int x = t + delay; x < Math.min(t + forget, n + 1); x++)
                dp[x] = (dp[x] + dp[t]) % MOD;

        // sum last `forget` days to count people who still remember at day T
        int res = 0;
        for (int i = n - forget + 1; i <= n; i++)
            if (i >= 1)
                res = (res + dp[i]) % MOD;

        return res;
    }
}