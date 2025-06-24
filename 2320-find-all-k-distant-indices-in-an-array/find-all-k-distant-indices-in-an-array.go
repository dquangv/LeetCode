func findKDistantIndices(nums []int, key int, k int) []int {
	n := len(nums)
	diff := make([]int, n+1)
	result := []int{}

	for i := 0; i < n; i++ {
		if nums[i] == key {
			start := max(0, i-k)
			end := min(n, i+k+1)
			diff[start]++
			if end < n {
				diff[end]--
			}
		}
	}

	sum := 0
	for i := 0; i < n; i++ {
		sum += diff[i]
		if sum > 0 {
			result = append(result, i)
		}
	}

	return result
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
