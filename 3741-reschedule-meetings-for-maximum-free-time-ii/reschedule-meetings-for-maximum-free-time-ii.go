func maxFreeTime(eventTime int, startTime []int, endTime []int) int {
	n := len(startTime)
	lastEnd := 0
	maxLeft := 0
	res := 0

	gaps := make([]int, n+1)

	for i := 0; i < n; i++ {
		gaps[i] = startTime[i] - lastEnd
		lastEnd = endTime[i]
	}
	gaps[n] = eventTime - lastEnd

	maxRight := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		if gaps[i+1] > maxRight[i+1] {
			maxRight[i] = gaps[i+1]
		} else {
			maxRight[i] = maxRight[i+1]
		}
	}

	for i := 1; i <= n; i++ {
		dur := endTime[i-1] - startTime[i-1]

		if maxLeft >= dur || maxRight[i] >= dur {
			tmp := gaps[i-1] + dur + gaps[i]
			if tmp > res {
				res = tmp
			}
		}
		if sum := gaps[i-1] + gaps[i]; sum > res {
			res = sum
		}
		if gaps[i-1] > maxLeft {
			maxLeft = gaps[i-1]
		}
	}

	return res
}