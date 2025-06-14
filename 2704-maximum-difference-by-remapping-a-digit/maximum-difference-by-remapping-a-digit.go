func minMaxDifference(num int) int {
	firstNonNine, firstDigit := -1, -1
	remaining := num

	// Tìm chữ số đầu tiên khác 9 và chữ số đầu tiên (từ phải sang)
	for remaining > 0 {
		digit := remaining % 10
		if digit != 9 {
			firstNonNine = digit
		}
		firstDigit = digit
		remaining /= 10
	}

	remaining = num
	minVal, maxVal := 0, 0
	multiplier := 1

	// Tạo số min và max
	for remaining > 0 {
		digit := remaining % 10
		minDigit, maxDigit := digit, digit

		if digit == firstDigit {
			minDigit = 0
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