func maxEvents(events [][]int) int {
	// Sort events by end day (greedy)
	sort.Slice(events, func(i, j int) bool {
		return events[i][1] < events[j][1]
	})

	// Find maximum day for size of union-find array
	maxDay := 0
	for _, e := range events {
		if e[1] > maxDay {
			maxDay = e[1]
		}
	}

	// Union-Find parent array
	parent := make([]int, maxDay+2)
	for i := 0; i < len(parent); i++ {
		parent[i] = i
	}

	// Find with path compression
	var find func(int) int
	find = func(x int) int {
		if parent[x] != x {
			parent[x] = find(parent[x])
		}
		return parent[x]
	}

	count := 0
	for _, e := range events {
		start, end := e[0], e[1]
		day := find(start)
		if day <= end {
			count++
			parent[day] = find(day + 1) // mark day as taken
		}
	}
	return count
}
