func getSneakyNumbers(nums []int) []int {
	n := len(nums) - 2
	for i := 0; i < len(nums); i++ {
		idx := nums[i] % n
		nums[idx] += n
	}

	var ans []int
	for i := 0; i < n; i++ {
		if nums[i]/n >= 2 {
			ans = append(ans, i)
		}
	}
    
	return ans
}