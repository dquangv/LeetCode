func rangeAddQueries(n int, queries [][]int) [][]int {
	result := make([][]int, n)
	for i := range result {
		result[i] = make([]int, n+1)
	}

	for _, query := range queries {
		for r := query[0]; r <= query[2]; r++ {
			result[r][query[1]]++
			result[r][query[3]+1]--
		}
	}

	for r := range result {
		for c := 1; c < n; c++ {
			result[r][c] += result[r][c-1]
		}
		result[r] = result[r][:n]
	}
    
	return result
}