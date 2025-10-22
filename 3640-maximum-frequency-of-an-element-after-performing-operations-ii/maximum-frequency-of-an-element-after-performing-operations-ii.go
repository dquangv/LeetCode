func calcFreqs(nums []int) []int {
	freqs := make([]int, len(nums))
	for i := 0; i < len(nums); i++ {
		k := i
		for ; k < len(nums) && nums[k] == nums[i]; k++ {
		}
		for j := i; j < k; j++ {
			freqs[j] = k - i
		}
		i = k - 1
	}
	return freqs
}

func maxFrequency(nums []int, k int, numOperations int) int {
	slices.Sort(nums)

	freqs := calcFreqs(nums)
	var maxFreq int

	l, r := 0, 1
	for i := range nums {
		for ; r < len(nums) && nums[r] <= nums[i]+k; r++ {
		}
		for ; nums[l] < nums[i]-k; l++ {
		}
		maxFreq = max(freqs[i]+min(r-l-freqs[i], numOperations), maxFreq)
	}

	l, r = 0, 1
	for ; l < len(nums); l++ {
		for r < len(nums) && nums[r]-nums[l] <= 2*k {
			r++
		}
		maxFreq = max(min(r-l, numOperations), maxFreq)
	}

	return maxFreq
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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