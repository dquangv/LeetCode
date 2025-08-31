func solveSudoku(board [][]byte) {
	rows := make([]map[byte]bool, 9)
	cols := make([]map[byte]bool, 9)
	blocks := make([]map[byte]bool, 9)
	emptyCells := [][2]int{}

	// Initialize maps
	for i := 0; i < 9; i++ {
		rows[i] = make(map[byte]bool)
		cols[i] = make(map[byte]bool)
		blocks[i] = make(map[byte]bool)
	}

	// Pre-fill sets with existing numbers
	for r := 0; r < 9; r++ {
		for c := 0; c < 9; c++ {
			if board[r][c] == '.' {
				emptyCells = append(emptyCells, [2]int{r, c})
			} else {
				val := board[r][c]
				rows[r][val] = true
				cols[c][val] = true
				b := (r/3)*3 + (c / 3)
				blocks[b][val] = true
			}
		}
	}

	var backtrack func(int) bool
	backtrack = func(k int) bool {
		if k == len(emptyCells) {
			return true
		}
		r, c := emptyCells[k][0], emptyCells[k][1]
		b := (r/3)*3 + (c / 3)

		for ch := byte('1'); ch <= '9'; ch++ {
			if !rows[r][ch] && !cols[c][ch] && !blocks[b][ch] {
				board[r][c] = ch
				rows[r][ch] = true
				cols[c][ch] = true
				blocks[b][ch] = true

				if backtrack(k + 1) {
					return true
				}

				// backtrack
				board[r][c] = '.'
				delete(rows[r], ch)
				delete(cols[c], ch)
				delete(blocks[b], ch)
			}
		}
		return false
	}

	backtrack(0)
}