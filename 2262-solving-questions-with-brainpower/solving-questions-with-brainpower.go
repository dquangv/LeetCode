func mostPoints(questions [][]int) int64 {
    n := len(questions);
    dp := make([]int64, n + 1);
    dp[n - 1] = int64(questions[n - 1][0]);
    
    for i := n - 2; i >= 0; i-- {
        dp[i] = int64(questions[i][0]);

        nextIndex := i + questions[i][1] + 1;
        solve := int64(questions[i][0]);
        if nextIndex < n {
            solve += dp[nextIndex];
        }

        dp[i] = max(solve, dp[i + 1]);
    }

    return dp[0];
}

func max(a, b int64) int64 {
    if a > b {
        return a;
    }

    return b;
}