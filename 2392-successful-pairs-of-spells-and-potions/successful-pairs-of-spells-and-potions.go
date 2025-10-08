func successfulPairs(spells []int, potions []int, success int64) []int {
	sort.Ints(potions)
	resp := []int{}
	totalLength := len(potions)
	for _, item := range spells {
		totalItem := totalLength - binarySearch(potions, item, success)
		resp = append(resp, totalItem)
	}
    
	return resp
}

func binarySearch(arr []int, currentSpell int, success int64) int {
	low := 0
	high := len(arr) - 1

	for low <= high {
		mid := low + (high-low)/2

		if int64(arr[mid]*currentSpell) >= success {
			high = mid - 1
		} else {
			low = mid + 1
		}
	}

	return low
}