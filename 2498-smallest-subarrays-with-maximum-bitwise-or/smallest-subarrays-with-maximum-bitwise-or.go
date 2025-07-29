func smallestSubarrays(nums []int) []int {
	n := len(nums)
	lastSeen := make([]int, 30) // Track last seen index for each bit
	res := make([]int, n)

	// Initialize result with 1 since minimum subarray length is 1
	for i := range res {
		res[i] = 1
	}

	// Traverse from right to left
	for i := n - 1; i >= 0; i-- {
		for bit := 0; bit < 30; bit++ {
			if (nums[i] & (1 << bit)) > 0 {
				lastSeen[bit] = i
			}
			if lastSeen[bit] > 0 {
				res[i] = max(res[i], lastSeen[bit]-i+1)
			}
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
