func findLucky(arr []int) int {
    freq := make([]int, 501)
	maxVal := 0
	result := -1

	for _, num := range arr {
		freq[num]++
		if num > maxVal {
			maxVal = num
		}
	}

	for i := maxVal; i > 0; i-- {
		if freq[i] == i {
			result = i
			break
		}
	}

	return result
}