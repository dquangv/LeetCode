func findMaxForm(strs []string, m int, n int) int {
	if len(strs) == 0 {
		return 0
	}

	return solve(strs, make(map[string]int), m, n, 0)
}

func solve(strs []string, mp map[string]int, m, n, index int) int {
	if m == 0 && n == 0 {
		return 0
	}

	if index >= len(strs) {
		return 0
	}

	key := fmt.Sprintf("%d:%d:%d", m, n, index)
	if _, ok := mp[key]; ok {
		return mp[key]
	}

	totalCnt, curr := 0, strs[index]
	ones, zeroes := 0, 0

	for i := range curr {
		if curr[i] == '0' {
			zeroes++
		} else {
			ones++
		}
	}

	takenStrCnt := 0
	if ones <= n && zeroes <= m {
		takenStrCnt += 1 + solve(strs, mp, m-zeroes, n-ones, index+1)
	}

	skippedStrNum := solve(strs, mp, m, n, index+1)
	totalCnt = skippedStrNum
	if takenStrCnt > skippedStrNum {
		totalCnt = takenStrCnt
	}

	mp[key] = totalCnt
	return totalCnt
}