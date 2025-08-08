var optionA = []int{100, 75, 50, 25}
var optionB = []int{0, 25, 50, 75}

func solve(a, b int, dp [][]float64) float64 {
	if a == 0 && b == 0 {
		return 0.5 // half the probability that A and B become empty at the same time
	}
	if a == 0 {
		return 1.0
	}
	if b == 0 {
		return 0.0
	}

	if dp[a][b] != -1 {
		return dp[a][b]
	}

	ans := 0.0
	for k := 0; k < 4; k++ {
		rema := a - optionA[k]
		remb := b - optionB[k]
		if rema < 0 {
			rema = 0
		}
		if remb < 0 {
			remb = 0
		}
		ans += 0.25 * solve(rema, remb, dp)
	}

	dp[a][b] = ans
	return ans
}

func soupServings(n int) float64 {
	if n >= 4800 {
		return 1.0
	}

	// create dp with initial value -1
	dp := make([][]float64, n+1)
	for i := range dp {
		dp[i] = make([]float64, n+1)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}

	return solve(n, n, dp)
}