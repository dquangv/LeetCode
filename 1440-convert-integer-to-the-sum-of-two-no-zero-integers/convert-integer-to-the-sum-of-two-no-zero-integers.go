func getNoZeroIntegers(n int) []int {
	isNoZero := func(x int) bool {
		for x > 0 {
			if x%10 == 0 {
				return false
			}
			x /= 10
		}
        
		return true
	}

	for a := 1; a < n; a++ {
		b := n - a
		if isNoZero(a) && isNoZero(b) {
			return []int{a, b}
		}
	}

	return []int{}
}
