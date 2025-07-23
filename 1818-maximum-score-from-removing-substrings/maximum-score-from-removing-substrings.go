func maximumGain(s string, x int, y int) int {
	maxChar, minChar := 'a', 'b'
	maxVal, minVal := x, y

	if y > x {
		maxChar, minChar = 'b', 'a'
		maxVal, minVal = y, x
	}

	ans := 0
	maxCharCount, minCharCount := 0, 0

	for _, ch := range s {
		if ch == rune(minChar) {
			if maxCharCount > 0 {
				ans += maxVal
				maxCharCount--
			} else {
				minCharCount++
			}
		} else if ch == rune(maxChar) {
			maxCharCount++
		} else {
			ans += min(minCharCount, maxCharCount) * minVal
			minCharCount, maxCharCount = 0, 0
		}
	}

	ans += min(minCharCount, maxCharCount) * minVal
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
