type Point struct {
	x, y int
}

func numberOfPairs(points [][]int) int {
	// Step 1: Sort by x ascending, and for ties, y descending
	sort.Slice(points, func(i, j int) bool {
		if points[i][0] == points[j][0] {
			return points[i][1] > points[j][1]
		}
		return points[i][0] < points[j][0]
	})

	cnt := 0

	// Step 2: Iterate each point A = (x, y)
	for i := 0; i < len(points)-1; i++ {
		y := points[i][1]
		lower := -1

		// Step 3: Check candidate B points to the right
		for j := i + 1; j < len(points); j++ {
			if lower < points[j][1] && points[j][1] <= y {
				// ✅ Found a valid pair (A, B)
				cnt++
				lower = points[j][1]
			}
			if lower == y {
				break
			}
		}
	}

	// Step 4: Return result
	return cnt
}