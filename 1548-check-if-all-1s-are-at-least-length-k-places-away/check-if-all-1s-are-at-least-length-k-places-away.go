func kLengthApart(nums []int, k int) bool {
	prev := -1
    
	for i, val := range nums {
		if val == 1 {
			if prev != -1 && (i-prev-1) < k {
				return false
			}
			prev = i
		}
	}

	return true
}