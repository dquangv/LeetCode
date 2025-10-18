func maxDistinctElements(nums []int, k int) int {
	sort.Ints(nums)
	last := nums[0] - k - 1
	count := 0

	for _, num := range nums {
		if num-k > last {
			last = num - k
		} else if num+k <= last {
			continue
		} else {
			last++
		}
		count++
	}

	return count
}