func productQueries(n int, queries [][]int) []int {
	const MOD = 1_000_000_007
	powers := []int{}

	// Step 1: Build the 'powers' array by checking each bit of n
	for i := 0; i < 32; i++ {
		if (n & (1 << i)) != 0 {
			powers = append(powers, 1<<i) // store this power of two
		}
	}

	ans := make([]int, len(queries))

	// Step 2: Process each query
	for qIndex, q := range queries {
		left, right := q[0], q[1]

		// Start with the first element in the range
		product := int64(powers[left])

		// Multiply all elements from left+1 to right
		for i := left + 1; i <= right; i++ {
			product = (product * int64(powers[i])) % MOD
		}

		ans[qIndex] = int(product)
	}

	return ans
}