func countSquares(matrix [][]int) int {
	result := 0

	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0]); j++ {
			if matrix[i][j] == 1 {
				// look at top, left, and diagonal neighbors
				top := 0
				left := 0
				diag := 0

				if i > 0 {
					top = matrix[i-1][j]
				}

				if j > 0 {
					left = matrix[i][j-1]
				}

				if i > 0 && j > 0 {
					diag = matrix[i-1][j-1]
				}

				// update DP value
				if i > 0 && j > 0 {
					matrix[i][j] += min(top, min(left, diag))
				}

				result += matrix[i][j]
			}
		}
	}

	return result
}

func min(a, b int) int {
	if a < b {
		return a
	}
    
	return b
}