func reorderedPowerOf2(n int) bool {
	count := digitCount(n)

	power := 1 // start from 2^0 = 1
	for i := 0; i < 31; i++ {
		// Get digit count of current power of two
		powerCount := digitCount(power)

		// Compare digit counts
		if equalArrays(count, powerCount) {
			return true
		}

		power *= 2 // move to next power of two
	}
	return false
}

// Counts occurrences of each digit (0–9)
func digitCount(n int) [10]int {
	var count [10]int
	for n != 0 {
		count[n%10]++
		n /= 10
	}
	return count
}

// Compares two digit frequency arrays
func equalArrays(a, b [10]int) bool {
	for i := 0; i < 10; i++ {
		if a[i] != b[i] {
			return false
		}
	}
	return true
}