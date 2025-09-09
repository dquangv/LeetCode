const MOD = 1000000007

func peopleAwareOfSecret(n int, delay int, forget int) int {
	// dp[t] = how many people learn the secret on day t
	dp := make([]int, n+1)
	dp[1] = 1 // day 1: one person knows the secret

	for t := 1; t <= n; t++ {
		people := dp[t] // number of people who got the secret on day t

		// they will share from (t+delay) to (t+forget-1)
		for x := t + delay; x < min(t+forget, n+1); x++ {
			dp[x] = (dp[x] + people) % MOD
		}
	}

	// At the end, only those who learned within last `forget-1` days still remember
	res := 0
	for i := n - forget + 1; i <= n; i++ {
		if i >= 1 {
			res = (res + dp[i]) % MOD
		}
	}

	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
    
	return b
}