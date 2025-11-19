func findFinalValue(nums []int, original int) int {
	m := map[int]bool{}
	for _, num := range nums {
		m[num] = true
	}
    
	for true {
		if m[original] == true {
			original *= 2
		} else {
			break
		}
	}

	return original
}