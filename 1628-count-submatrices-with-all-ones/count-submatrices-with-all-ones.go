func numSubmat(mat [][]int) int {
	if len(mat) == 0 {
		return 0
	}
	m, n := len(mat), len(mat[0])
	result := 0

	// Step 1: Run-Length Encoding (RLE) per row
	for i := 0; i < m; i++ {
		for j := 1; j < n; j++ {
			if mat[i][j] == 1 {
				mat[i][j] += mat[i][j-1]
			}
		}
	}

	// Step 2: Count rectangles
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans := mat[i][j]
			for k := i; k < m; k++ {
				if mat[k][j] < ans {
					ans = mat[k][j]
				}
				result += ans
			}
		}
	}

	return result
}