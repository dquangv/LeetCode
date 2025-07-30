func longestSubarray(nums []int) int {
	// Step 1: Find the maximum value
	maxVal := nums[0]
	for _, num := range nums {
		if num > maxVal {
			maxVal = num
		}
	}

	maxLen := 1
	count := 0
	i := 0

	// Step 2: Iterate to find longest streak of maxVal
	for i < len(nums) {
		if nums[i] == maxVal {
			for i < len(nums) && nums[i] == maxVal {
				count++
				i++
			}
			if count > maxLen {
				maxLen = count
			}
			count = 0
		} else {
			i++
		}
	}

	return maxLen
}
