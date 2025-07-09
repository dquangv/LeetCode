func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(startTime)
	gaps := make([]int, n+1)

	// gaps[0]: free time before first meeting
	gaps[0] = startTime[0]

	// gaps[i]: time between meeting i-1 and i
	for i := 1; i < n; i++ {
		gaps[i] = startTime[i] - endTime[i-1]
	}

	// gaps[n]: free time after last meeting until event ends
	gaps[n] = eventTime - endTime[n-1]

	// Size of sliding window is at most k+1
	sz := k + 1
	if sz > len(gaps) {
		sz = len(gaps)
	}

	// Use sliding window to find max sum of sz consecutive gaps
	curr, best := 0, 0
	for i := 0; i < sz; i++ {
		curr += gaps[i]
	}
	best = curr

	for i := sz; i < len(gaps); i++ {
		curr += gaps[i] - gaps[i-sz]
		if curr > best {
			best = curr
		}
	}

	return best
}