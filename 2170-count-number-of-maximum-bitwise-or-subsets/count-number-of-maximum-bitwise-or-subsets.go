func countMaxOrSubsets(nums []int) int {
	maxOr := 0
	for _, n := range nums {
		maxOr |= n // Compute maximum OR possible
	}

	var dfs func(i, currOr int) int
	dfs = func(i, currOr int) int {
		if i == len(nums) {
			if currOr == maxOr {
				return 1
			}
			return 0
		}
		// Include nums[i]
		take := dfs(i+1, currOr|nums[i])
		// Exclude nums[i]
		skip := dfs(i+1, currOr)
		return take + skip
	}

	return dfs(0, 0)
}
