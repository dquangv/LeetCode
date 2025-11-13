func maxOperations(binaryString string) int {
	countOnes := int(binaryString[0] - '0')
	maxOperations := 0

	for i := 1; i < len(binaryString); i++ {
		countOnes += int(binaryString[i] - '0')
		maxOperations += Ternary(binaryString[i-1] > binaryString[i], countOnes, 0)
	}

	return maxOperations
}

func Ternary[T any](condition bool, first T, second T) T {
	if condition {
		return first
	}

	return second
}
