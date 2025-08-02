func minCost(basket1 []int, basket2 []int) int64 {
	count := make(map[int]int)

	// Count frequency difference of each fruit between basket1 and basket2
	for _, v := range basket1 {
		count[v]++
	}
	for _, v := range basket2 {
		count[v]--
	}

	swaps := []int{}
	minValue := int(^uint(0) >> 1) // Max int
	for k, v := range count {
		if v%2 != 0 {
			return -1 // Impossible if any count is odd
		}
		if k < minValue {
			minValue = k // Track smallest fruit cost
		}
		for i := 0; i < abs(v)/2; i++ {
			swaps = append(swaps, k)
		}
	}

	sort.Ints(swaps)
	var res int64 = 0
	n := len(swaps)
	for i := 0; i < n/2; i++ {
		// Choose between direct swap or two swaps via the smallest fruit
		cost := min(swaps[i], 2*minValue)
		res += int64(cost)
	}
	return res
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
