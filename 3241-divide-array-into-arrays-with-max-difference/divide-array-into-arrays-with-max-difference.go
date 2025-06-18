func divideArray(nums []int, k int) [][]int {
	sort.Ints(nums)
	n := len(nums)
	if n%3 != 0 {
		return [][]int{}
	}

	result := [][]int{}
	for i := 0; i < n; i += 3 {
		a, b, c := nums[i], nums[i+1], nums[i+2]
		if c-a > k {
			return [][]int{}
		}
		result = append(result, []int{a, b, c})
	}
	return result
}