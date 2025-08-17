func threeSum(nums []int) [][]int {
	res := [][]int{}
	sort.Ints(nums) // Step 1: Sort array

	for i := 0; i < len(nums)-2; i++ {
		// Skip duplicate first elements
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}

		left, right := i+1, len(nums)-1
		for left < right {
			total := nums[i] + nums[left] + nums[right]
			if total == 0 {
				// Found a valid triplet
				res = append(res, []int{nums[i], nums[left], nums[right]})

				// Skip duplicate values for left
				for left < right && nums[left] == nums[left+1] {
					left++
				}
				// Skip duplicate values for right
				for left < right && nums[right] == nums[right-1] {
					right--
				}
				left++
				right--
			} else if total < 0 {
				left++
			} else {
				right--
			}
		}
	}

	return res
}