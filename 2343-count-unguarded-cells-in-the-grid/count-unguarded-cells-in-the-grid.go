func countUnguarded(m int, n int, guards [][]int, walls [][]int) int {
	grid := make([]uint8, m*n)

	for _, pos := range guards {
		grid[pos[0]*n+pos[1]] = 2
	}

	for _, pos := range walls {
		grid[pos[0]*n+pos[1]] = 2
	}

	dx := [4]int{-1, 0, 1, 0}
	dy := [4]int{0, 1, 0, -1}

	for _, guard := range guards {
		for k := 0; k < 4; k++ {
			x, y := guard[0], guard[1]
			newX, newY := x+dx[k], y+dy[k]

			for newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX*n+newY] < 2 {
				grid[newX*n+newY] = 1
				newX += dx[k]
				newY += dy[k]
			}
		}
	}

	unguardedCount := m*n - len(guards) - len(walls)
	for i := 0; i < m*n; i++ {
		if grid[i] == 1 {
			unguardedCount--
		}
	}

	return unguardedCount
}