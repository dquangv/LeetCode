func numOfUnplacedFruits(fruits []int, baskets []int) int {
	n := len(fruits)
	alloted := 0

	for i := 0; i < n; i++ { // For each fruit type
		for j := 0; j < n; j++ { // Check each basket
			if fruits[i] <= baskets[j] {
				alloted++ // Placed the fruit
				baskets[j] = -1 // Mark basket as used
				break // Move to next fruit
			}
		}
	}

	return n - alloted // Return number of fruits that were not placed
}