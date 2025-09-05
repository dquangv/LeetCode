func makeTheIntegerZero(num1 int, num2 int) int {
	for k := 1; k <= 60; k++ {
		x := num1 - num2*k

		// If remaining x < k, not possible
		if x < k {
			return -1
		}

		// Count set bits in x
		if bits.OnesCount(uint(x)) <= k {
			return k
		}
	}
    
	return -1
}