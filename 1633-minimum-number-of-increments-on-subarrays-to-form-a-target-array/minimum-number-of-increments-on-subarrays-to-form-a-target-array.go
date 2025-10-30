func minNumberOperations(target []int) int {
	cnt := 0
	prev := 0

	for i := 0; i < len(target); i++ {
		if target[i] > prev {
			cnt += (target[i] - prev)
		}
		prev = target[i]
	}
    
	return cnt
}