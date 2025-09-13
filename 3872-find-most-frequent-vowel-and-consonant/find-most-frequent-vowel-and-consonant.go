func maxFreqSum(s string) int {
	// freq map for counting characters
	freq := make(map[rune]int)
	for _, ch := range s {
		freq[ch]++
	}

	// maxCV[0] = consonant, maxCV[1] = vowel
	maxCV := [2]int{0, 0}

	for ch, f := range freq {
		// Check if vowel
		idx := 0
		if ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' {
			idx = 1
		}

		if f > maxCV[idx] {
			maxCV[idx] = f
		}
	}

	return maxCV[0] + maxCV[1]
}