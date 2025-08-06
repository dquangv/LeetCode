func numOfUnplacedFruits(fruits []int, baskets []int) int {
	n := len(baskets)

	// Find the smallest power of 2 >= n for segment tree size
	N := 1
	for N < n {
		N <<= 1
	}

	// Segment tree array, initialized with zeroes
	segTree := make([]int, 2*N)

	// Insert basket capacities at leaves of segment tree
	for i := 0; i < n; i++ {
		segTree[N+i] = baskets[i]
	}

	// Build the segment tree bottom-up
	for i := N - 1; i > 0; i-- {
		segTree[i] = max(segTree[2*i], segTree[2*i+1])
	}

	count := 0 // Unplaced fruit counter

	// Try placing each fruit
	for i := 0; i < n; i++ {
		x := fruits[i]
		index := 1 // Start from root of segment tree

		// If largest basket is too small, skip this fruit
		if segTree[index] < x {
			count++
			continue
		}

		// Traverse down to find leftmost valid basket
		for index < N {
			if segTree[2*index] >= x {
				index = 2 * index
			} else {
				index = 2*index + 1
			}
		}

		// Mark basket as used
		segTree[index] = -1

		// Update segment tree bottom-up
		for index > 1 {
			index >>= 1
			segTree[index] = max(segTree[2*index], segTree[2*index+1])
		}
	}

	return count
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}