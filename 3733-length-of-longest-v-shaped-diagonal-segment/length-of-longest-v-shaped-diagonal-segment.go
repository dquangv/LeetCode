func lenOfVDiagonal(g [][]int) int {
	n, m := len(g), len(g[0])
	D := [][2]int{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}} // 4 diagonal directions
	inb := func(i, j int) bool { return i >= 0 && i < n && j >= 0 && j < m }

	res := 0

	// dp0[d][i][j] = alternating length starting with "0"
	// dp2[d][i][j] = alternating length starting with "2"
	dp0 := make([][][]int, 4)
	dp2 := make([][][]int, 4)
	for d := 0; d < 4; d++ {
		dp0[d] = make([][]int, n)
		dp2[d] = make([][]int, n)
		for i := range dp0[d] {
			dp0[d][i] = make([]int, m)
			dp2[d][i] = make([]int, m)
		}
	}

	// Fill DP arrays
	for d, dir := range D {
		dr, dc := dir[0], dir[1]
		rows := make([]int, 0)
		cols := make([]int, 0)

		if dr >= 0 {
			for i := n - 1; i >= 0; i-- {
				rows = append(rows, i)
			}
		} else {
			for i := 0; i < n; i++ {
				rows = append(rows, i)
			}
		}

		if dc >= 0 {
			for j := m - 1; j >= 0; j-- {
				cols = append(cols, j)
			}
		} else {
			for j := 0; j < m; j++ {
				cols = append(cols, j)
			}
		}

		for _, i := range rows {
			for _, j := range cols {
				ni, nj := i+dr, j+dc
				if g[i][j] == 0 {
					if inb(ni, nj) {
						dp0[d][i][j] = 1 + dp2[d][ni][nj]
					} else {
						dp0[d][i][j] = 1
					}
				}
				if g[i][j] == 2 {
					if inb(ni, nj) {
						dp2[d][i][j] = 1 + dp0[d][ni][nj]
					} else {
						dp2[d][i][j] = 1
					}
				}
			}
		}
	}

	// Try every starting point with "1"
	for d, dir := range D {
		dr, dc := dir[0], dir[1]
		t := (d + 1) % 4 // clockwise turn

		for i := 0; i < n; i++ {
			for j := 0; j < m; j++ {
				if g[i][j] != 1 {
					continue
				}
				r, c, need, L := i, j, 1, 0
				for inb(r, c) && g[r][c] == need {
					L++
					if L > res {
						res = L
					}

					// Try turning clockwise
					tr, tc := r+D[t][0], c+D[t][1]
					if inb(tr, tc) {
						if L%2 == 1 {
							res = max(res, L+dp2[t][tr][tc])
						} else {
							res = max(res, L+dp0[t][tr][tc])
						}
					}

					// Move forward
					r, c = r+dr, c+dc
					if need == 1 {
						need = 2
					} else if need == 2 {
						need = 0
					} else {
						need = 2
					}
				}
			}
		}
	}

	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}