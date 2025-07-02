const MOD int = 1e9 + 7

func possibleStringCount(word string, k int) int {
	n := len(word)
	if n < k {
		return 0
	}
	if n == k {
		return 1
	}

	// Tách các đoạn ký tự giống nhau
	seg := []int{1}
	for i := 1; i < n; i++ {
		if word[i] == word[i-1] {
			seg[len(seg)-1]++
		} else {
			seg = append(seg, 1)
		}
	}
	m := len(seg)

	// Tính tổng số tổ hợp chuỗi gốc
	total := 1
	takeMod := false
	for _, x := range seg {
		total *= x
		if total >= MOD {
			total %= MOD
			takeMod = true
		}
	}
	if total == 1 && !takeMod {
		return 1
	}
	if k <= m {
		return total
	}

	// Đếm số tổ hợp không hợp lệ có độ dài < k
	maxT := k - m - 1
	dp := make([][]int, 2)
	for i := range dp {
		dp[i] = make([]int, maxT+1)
	}
	dp[0][0] = 1

	for j := 0; j < m; j++ {
		s := seg[j]
		prefix := make([]int, maxT+2)
		for i := 0; i <= maxT; i++ {
			prefix[i+1] = (prefix[i] + dp[j%2][i]) % MOD
		}
		for i := 0; i <= maxT; i++ {
			L := max(0, i-(s-1))
			R := i
			dp[(j+1)%2][i] = (prefix[R+1] - prefix[L] + MOD) % MOD
		}
	}

	lessK := 0
	for _, x := range dp[m%2] {
		lessK = (lessK + x) % MOD
	}
	return (total - lessK + MOD) % MOD
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}