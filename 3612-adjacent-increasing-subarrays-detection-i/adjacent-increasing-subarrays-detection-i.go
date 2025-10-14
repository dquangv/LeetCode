
func hasIncreasingSubarrays(nums []int, k int) bool {
	n := len(nums)
	if n < 2*k {
		return false
	}

	for i := 0; i <= n-2*k; i++ {
		if isStrictlyInc(nums, i, k) && isStrictlyInc(nums, i+k, k) {
			return true
		}
	}

	return false
}

func isStrictlyInc(nums []int, start int, k int) bool {
	for i := start; i < start+k-1; i++ {
		if nums[i] >= nums[i+1] {
			return false
		}
	}
    
	return true
}