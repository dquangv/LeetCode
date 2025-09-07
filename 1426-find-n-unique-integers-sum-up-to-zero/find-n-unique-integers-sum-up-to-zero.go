func sumZero(n int) []int {
	res := make([]int, n)

	res[0] = n * (1 - n) / 2

	for i := 1; i < n; i++ {
		res[i] = i
	}

	return res
}