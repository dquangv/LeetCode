func mostBooked(n int, meetings [][]int) int {
	sort.Slice(meetings, func(i, j int) bool {
		return meetings[i][0] < meetings[j][0]
	})

	roomCount := make([]int, n)
	roomTimes := make([]int64, n)

	for _, meeting := range meetings {
		start, end := int64(meeting[0]), int64(meeting[1])
		assigned := false
		earliest := int64(math.MaxInt64)
		minIndex := -1

		for i := 0; i < n; i++ {
			if roomTimes[i] < earliest {
				earliest = roomTimes[i]
				minIndex = i
			}
			if roomTimes[i] <= start {
				roomTimes[i] = end
				roomCount[i]++
				assigned = true
				break
			}
		}

		if !assigned {
			roomTimes[minIndex] += end - start
			roomCount[minIndex]++
		}
	}

	// Find the room with max meeting count
	maxCount, result := -1, -1
	for i := 0; i < n; i++ {
		if roomCount[i] > maxCount {
			maxCount = roomCount[i]
			result = i
		}
	}
	return result
}