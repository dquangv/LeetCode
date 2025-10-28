func countValidSelections(nums []int) int {
	n, rs, sum := len(nums), 0, 0
	ps, ss := make([]int, n), make([]int, n)
	for i := 0; i < n; i++ {
		ps[i] = sum
		sum += nums[i]
	}
    
	sum = 0
	for i := n - 1; i >= 0; i-- {
		ss[i] = sum
		sum += nums[i]
	}

	for i, num := range nums {
		if num != 0 {
			continue
		}

		if ps[i] == ss[i] {
			rs += 2
		} else if ps[i]-ss[i] == 1 || ps[i]-ss[i] == -1 {
			rs += 1
		}
	}

	return rs
}