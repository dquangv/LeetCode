func minScoreTriangulation(A []int) int {
	memo := make([][]int, len(A))

	for i := range memo {
		memo[i] = make([]int, len(A))
		// If sub-array contains less than 3 vertices, score must be zero.
		for j := i + 2; j < len(A); j += 1 {
			memo[i][j] = -1
		}
	}

	return helper(A, 0, len(A)-1, memo)
}

func helper(A []int, start int, end int, memo [][]int) int {
	if memo[start][end] != -1 {
		return memo[start][end]
	}

	size := end - start
	best := -1

	// There are (size - 2) triangles to be considered.
	for k := 1; k < size; k += 1 {
		// Each value of k represents a triangle and 2 (possibly trivial) subproblems.
		t := A[start]*A[start+k]*A[end] +
			helper(A, start, start+k, memo) +
			helper(A, start+k, end, memo)

		if t < best || best == -1 {
			best = t
		}
	}

	memo[start][end] = best
    
	return best
}