class Solution {
    // Define the modulo value to prevent overflow and handle large results
    private static final int MOD = 1_000_000_007;

    public int numberOfWays(int n, int x) {
        // dp[sum] = number of ways to form 'sum' using powers of unique integers
        long[] dp = new long[n + 1];

        dp[0] = 1; // Base case: 1 way to make sum 0 (choose nothing)

        // Loop through all possible integers whose x-th power is ≤ n
        for (int i = 1; Math.pow(i, x) <= n; i++) {
            // Compute i^x (the power of the current integer)
            int power = (int) Math.pow(i, x);

            // Iterate backwards to ensure each number is only used once
            for (int sum = n; sum >= power; sum--)
                // Add ways to form 'sum - power' to ways of forming 'sum'
                dp[sum] = (dp[sum] + dp[sum - power]) % MOD;
        }

        return (int) dp[n];
    }
}
