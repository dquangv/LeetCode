func sortMatrix(grid [][]int) [][]int {
	n := len(grid)
	diagonals := make(map[int][]int)

	// Step 1: Collect numbers by diagonals (key = i - j)
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			key := i - j
			diagonals[key] = append(diagonals[key], grid[i][j])
		}
	}

	// Step 2: Sort each diagonal
	for key, diag := range diagonals {
		if key >= 0 {
			// bottom-left: sort descending
			sort.Slice(diag, func(a, b int) bool {
				return diag[a] > diag[b]
			})
		} else {
			// top-right: sort ascending
			sort.Ints(diag)
		}
		diagonals[key] = diag
	}

	// Step 3: Place back into grid
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			key := i - j
			grid[i][j] = diagonals[key][0]
			diagonals[key] = diagonals[key][1:] // pop front
		}
	}

	return grid
}
