func minimumArea(grid [][]int) int {
	n, m := len(grid), len(grid[0])

	// Initialize boundaries
	l, u := math.MaxInt32, math.MaxInt32
	r, d := math.MinInt32, math.MinInt32

	// Traverse grid
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if grid[i][j] == 1 {
				if j < l {
					l = j
				}
				if i < u {
					u = i
				}
				if j > r {
					r = j
				}
				if i > d {
					d = i
				}
			}
		}
	}

	// Area of rectangle covering all 1s
	return (r - l + 1) * (d - u + 1)
}