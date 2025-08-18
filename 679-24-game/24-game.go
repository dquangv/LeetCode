func judgePoint24(cards []int) bool {
	// Convert input to float64 since division is real (not integer)
	nums := make([]float64, len(cards))
	for i, v := range cards {
		nums[i] = float64(v)
	}

	// Recursive DFS function
	var dfs func([]float64) bool
	dfs = func(nums []float64) bool {
		// Base case: only one number left, check if it's (almost) 24
		if len(nums) == 1 {
			return math.Abs(nums[0]-24) < 1e-6
		}

		n := len(nums)
		// Try all pairs (i, j)
		for i := 0; i < n; i++ {
			for j := i + 1; j < n; j++ {
				num1, num2 := nums[i], nums[j]

				// Build a new list without nums[i] and nums[j]
				nextNums := make([]float64, 0, n-1)
				for k := 0; k < n; k++ {
					if k != i && k != j {
						nextNums = append(nextNums, nums[k])
					}
				}

				// All possible results of applying operators
				candidates := []float64{
					num1 + num2,
					num1 - num2,
					num2 - num1,
					num1 * num2,
				}

				// Try +, -, *
				for _, val := range candidates {
					if dfs(append(nextNums, val)) {
						return true
					}
				}

				// Try division (avoid division by zero)
				if math.Abs(num2) > 1e-6 {
					if dfs(append(nextNums, num1/num2)) {
						return true
					}
				}
				if math.Abs(num1) > 1e-6 {
					if dfs(append(nextNums, num2/num1)) {
						return true
					}
				}
			}
		}
		return false
	}

	return dfs(nums)
}