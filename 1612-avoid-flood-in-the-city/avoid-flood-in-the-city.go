func avoidFlood(rains []int) []int {
	res := make([]int, len(rains))
	for i := 0; i < len(res); i++ {
		if rains[i] > 0 {
			res[i] = -1
		} else {
			res[i] = 1 // You need to dry something; If we find a better lake to dry, we'll do that.
		}
	}

	rainedLakes := make(map[int]int)
	var dryDays []int
	for i, lake := range rains {
		if lake == 0 {
			dryDays = append(dryDays, i)
			continue
		}
		if prev, ok := rainedLakes[lake]; ok {
			// Now we need to find a dry day after we had rain in this lake previously
			// Either linear or binary search works
			if dryIdx := binarySearch(dryDays, prev); dryIdx != -1 {
				res[dryDays[dryIdx]] = lake
				dryDays = append(dryDays[:dryIdx], dryDays[dryIdx+1:]...)
			} else {
				return nil
			}
		}
        
		rainedLakes[lake] = i
	}

	return res
}

// binarySearch finds the next number greater than the current number.
// If none found, it returns -1
func binarySearch(nums []int, target int) int {
	ans := -1
	left, right := 0, len(nums)-1
	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] <= target {
			left = mid + 1
		} else {
			ans = mid
			right = mid - 1
		}
	}

	return ans
}