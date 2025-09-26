func triangleNumber(nums []int) int {
	sort.Ints(nums)
	start := 0

	for i, v := range nums {
		if v > 0 {
			start = i
			break
		}
	}

	total := 0
    
	for mid := start + 1; mid < len(nums)-1; mid++ {
		for short, long := start, mid+1; long < len(nums) && short < mid; {
			if nums[short]+nums[mid] > nums[long] {
				total += mid - short
				long++
			} else {
				short++
			}
		}
	}

	return total
}