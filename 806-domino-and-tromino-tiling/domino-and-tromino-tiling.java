class Solution {
    private static final int MOD = 1_000_000_007;
    private Integer[] memo;

    public int numTilings(int n) {
        memo = new Integer[n + 1];
        return dp(n);
    }

    private int dp(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        if (memo[n] != null)
            return memo[n];

        memo[n] = (int) (((2L * dp(n - 1)) % MOD + dp(n - 3)) % MOD);
        return memo[n];
    }
}