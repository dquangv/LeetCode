func totalFruit(fruits []int) int {
	n := len(fruits)
	lastFruit, secondLastFruit := -1, -1
	lastCount, currMax, max := 0, 0, 0

	for i := 0; i < n; i++ {
		fruit := fruits[i]

		// If the current fruit matches one of the two basket types, increase current max
		if fruit == lastFruit || fruit == secondLastFruit {
			currMax++
		} else {
			// Start new subarray from the last sequence of same fruit
			currMax = lastCount + 1
		}

		// If the current fruit is the same as lastFruit, increase its count
		if fruit == lastFruit {
			lastCount++
		} else {
			// Update basket types
			lastCount = 1
			secondLastFruit = lastFruit
			lastFruit = fruit
		}

		if currMax > max {
			max = currMax
		}
	}

	return max
}
