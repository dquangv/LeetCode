func numberOfPairs(points [][]int) int {
	// Step 1: Sort by x descending, if equal then y ascending
	sort.Slice(points, func(i, j int) bool {
		if points[i][0] == points[j][0] {
			return points[i][1] < points[j][1]
		}

		return points[i][0] > points[j][0]
	})

	n := len(points)
	ans := 0

	// Step 2: Iterate over Alice's position
	for i := 0; i < n-1; i++ {
		yi := points[i][1]
		y := 1 << 31 // initial upper bound

		// Step 3: Iterate over Bob's position
		for j := i + 1; j < n; j++ {
			yj := points[j][1]
			if y > yj && yj >= yi {
				ans++
				y = yj
				if yi == yj {
					break
				}
			}
		}
	}
    
	return ans
}