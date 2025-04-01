class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        dp[n - 1] = questions[n - 1][0];

        for (int i = n - 2; i >= 0; i--) {
            dp[i] = questions[i][0];

            int nextIndex = questions[i][1] + i + 1;
            long solve = questions[i][0] + (nextIndex < n ? dp[nextIndex] : 0);
            long skip = dp[i + 1];

            dp[i] = Math.max(solve, skip);
        }

        return dp[0];
    }
}