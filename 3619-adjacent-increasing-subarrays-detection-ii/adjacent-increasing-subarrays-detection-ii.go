func maxIncreasingSubarrays(nums []int) int {
	ans := 0
	last := nums[0]
	tempCount := 1
	lastIncreasingCount := 0

	for _, num := range nums[1:] {
		if num > last {
			tempCount++
			last = num
		} else {
			ans = max(ans, max(tempCount/2, min(lastIncreasingCount, tempCount)))
			lastIncreasingCount = tempCount
			tempCount = 1
			last = num
		}
	}

	ans = max(ans, max(tempCount/2, min(lastIncreasingCount, tempCount)))
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}