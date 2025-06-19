func partitionArray(nums []int, k int) int {
	sort.Ints(nums)
	result, i := 0, 0

	for i < len(nums) {
		start := nums[i]
		result++

		for i < len(nums) && nums[i]-start <= k {
			i++
		}
	}

	return result
}