func countHillValley(nums []int) int {
	count := 0
	left := 0 // Index of last non-equal element

	for i := 1; i < len(nums)-1; i++ {
		if nums[i] != nums[i+1] {
			if (nums[i] > nums[left] && nums[i] > nums[i+1]) ||
				(nums[i] < nums[left] && nums[i] < nums[i+1]) {
				count++
			}
			left = i // Update left for next round
		}
	}

	return count
}
