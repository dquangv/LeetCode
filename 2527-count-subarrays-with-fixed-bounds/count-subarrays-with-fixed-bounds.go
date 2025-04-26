func countSubarrays(nums []int, minK int, maxK int) int64 {
	var ans int64 = 0;
	maxPos, minPos := -1, -1;
	leftBound := 0;
	n := len(nums);

	for right := 0; right < n; right++ {
		x := nums[right];
		if x < minK || x > maxK {
			leftBound = right + 1;
			maxPos = -1;
			minPos = -1;
			continue;
		}

		if x == maxK {
			maxPos = right;
		}

		if x == minK {
			minPos = right;
		}

		validStart := min(maxPos, minPos);
		if validStart >= leftBound {
			ans += int64(validStart - leftBound + 1);
		}
	}

	return ans;
}

func min(a, b int) int {
	if a < b {
		return a;
	}
    
	return b;
}