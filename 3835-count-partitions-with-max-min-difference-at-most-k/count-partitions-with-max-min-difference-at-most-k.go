func countPartitions(nums []int, k int) int {
	const MOD int64 = 1000000007
	n := len(nums)

	dp := make([]int64, n+1)
	pref := make([]int64, n+1)
	dp[0] = 1
	pref[0] = 1

	minq := make([]int, 0, n)
	maxq := make([]int, 0, n)
	l := 0

	for r := 0; r < n; r++ {
		x := nums[r]
		for len(minq) > 0 && nums[minq[len(minq)-1]] >= x {
			minq = minq[:len(minq)-1]
		}
		minq = append(minq, r)

		for len(maxq) > 0 && nums[maxq[len(maxq)-1]] <= x {
			maxq = maxq[:len(maxq)-1]
		}
		maxq = append(maxq, r)

		for len(minq) > 0 && len(maxq) > 0 &&
			nums[maxq[0]]-nums[minq[0]] > k {

			if minq[0] == l {
				minq = minq[1:]
			}
			if maxq[0] == l {
				maxq = maxq[1:]
			}
			l++
		}

		var base int64
		if l > 0 {
			base = pref[l-1]
		} else {
			base = 0
		}

		val := (pref[r] - base) % MOD
		if val < 0 {
			val += MOD
		}

		dp[r+1] = val
		pref[r+1] = (pref[r] + dp[r+1]) % MOD
	}

	return int(dp[n] % MOD)
}
