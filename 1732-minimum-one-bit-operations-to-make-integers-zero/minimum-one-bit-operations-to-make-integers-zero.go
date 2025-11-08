func minimumOneBitOperations(n int) int {
	ans := 0
	mul := 1
	for n > 0 {
		shift := int(math.Log2(float64(n)))
		ans += ((1 << (shift + 1)) - 1) * mul
		n -= 1 << shift
		mul *= -1
	}
    
	return ans
}