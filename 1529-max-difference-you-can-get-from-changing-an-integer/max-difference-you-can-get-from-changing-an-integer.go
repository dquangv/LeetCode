func maxDiff(num int) int {
	return difference(num)
}

func difference(num int) int {
	firstNonNine, firstNonOne, firstDigit := -1, -1, -1
	remaining := num

	// Lặp để tìm các chữ số cần thay
	for rem := remaining; rem > 0; rem /= 10 {
		digit := rem % 10
		if digit != 9 {
			firstNonNine = digit
		}
		if digit > 1 {
			firstNonOne = digit
		}
		firstDigit = digit
	}

	minVal, maxVal := 0, 0
	multiplier := 1
	remaining = num

	// Lặp lại để tạo min và max
	for remaining > 0 {
		digit := remaining % 10
		minDigit, maxDigit := digit, digit

		if firstDigit == 1 && digit == firstNonOne {
			minDigit = 0
		}
		if firstDigit != 1 && digit == firstDigit {
			minDigit = 1
		}
		if digit == firstNonNine {
			maxDigit = 9
		}

		minVal += multiplier * minDigit
		maxVal += multiplier * maxDigit

		multiplier *= 10
		remaining /= 10
	}

	return maxVal - minVal
}