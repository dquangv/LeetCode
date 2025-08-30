func isValidSudoku(board [][]byte) bool {
    Row := make([]map[byte]bool, 9)
    Col := make([]map[byte]bool, 9)
    Block := make([]map[byte]bool, 9)
    
    for i := 0; i < 9; i++ {
        Row[i] = make(map[byte]bool)
        Col[i] = make(map[byte]bool)
        Block[i] = make(map[byte]bool)
    }

    for i := 0; i < 9; i++ {
        for j := 0; j < 9; j++ {
            c := board[i][j]
            if c == '.' {
                continue
            }

            // ✅ Check row
            if Row[i][c] {
                return false
            }
            Row[i][c] = true

            // ✅ Check column
            if Col[j][c] {
                return false
            }
            Col[j][c] = true

            // ✅ Check block
            idx := (i/3)*3 + (j/3)
            if Block[idx][c] {
                return false
            }
            Block[idx][c] = true
        }
    }

    return true
}