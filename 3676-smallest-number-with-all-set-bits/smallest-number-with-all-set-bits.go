func smallestNumber(n int) int {
	k := 1
	for {
		val := (1 << k) - 1
		if val >= n {
			return val
		}
		k++
	}
}