func findSmallestInteger(nums []int, value int) int {
	counter := make(map[int]int)
	for i, _ := range nums {
		nums[i] %= value
		if nums[i] < 0 {
			nums[i] += value
		}
		counter[nums[i]] += 1
	}
    
	for i := 0; i <= len(nums); i++ {
		if c, ok := counter[i%value]; !ok || c <= 0 {
			return i
		} else {
			counter[i%value] -= 1
		}
	}

	return 0
}