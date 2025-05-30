func isMatch(s string, p string) bool {
	m, n := len(s), len(p);
	dp := make([][]bool, m + 1);

	for i := range dp {
		dp[i] = make([]bool, n + 1);
	}

	dp[0][0] = true;

	// Xử lý pattern với dấu '*'
	for j := 1; j <= n; j++ {
		if p[j - 1] == '*' {
			dp[0][j] = dp[0][j - 2];
		}
	}

	// DP logic chính
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if p[j - 1] == s[i - 1] || p[j - 1] == '.' {
				dp[i][j] = dp[i - 1][j - 1];
			} else if p[j - 1] == '*' {
				dp[i][j] = dp[i][j - 2]; // bỏ qua ký tự đứng trước '*'

				if p[j - 2] == s[i - 1] || p[j - 2] == '.' {
					dp[i][j] = dp[i][j] || dp[i - 1][j];
				}
			}
		}
	}

	return dp[m][n];
}
