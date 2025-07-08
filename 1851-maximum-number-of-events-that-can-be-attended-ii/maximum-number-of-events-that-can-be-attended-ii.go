type Event struct {
	start, end, value int
}

func maxValue(events [][]int, k int) int {
	n := len(events)
	// Sort events by end day
	sort.Slice(events, func(i, j int) bool {
		return events[i][1] < events[j][1]
	})

	dp := make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, k+1)
	}

	// Helper function to find the last event that ends before given start
	binarySearch := func(currentStart int) int {
		left, right := 0, n-1
		res := -1
		for left <= right {
			mid := left + (right-left)/2
			if events[mid][1] < currentStart {
				res = mid
				left = mid + 1
			} else {
				right = mid - 1
			}
		}
		return res
	}

	for i := 1; i <= n; i++ {
		start, _, value := events[i-1][0], events[i-1][1], events[i-1][2]
		prev := binarySearch(start)
		for j := 1; j <= k; j++ {
			dp[i][j] = max(dp[i-1][j], dp[prev+1][j-1]+value)
		}
	}

	return dp[n][k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}