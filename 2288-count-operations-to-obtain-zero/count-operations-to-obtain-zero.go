func countOperations(num1 int, num2 int) int {
	a, b := num1, num2
	ops := 0
    
	for a > 0 && b > 0 {
		if a < b {
			a, b = b, a
		}
		ops += a / b
		a = a % b
	}

	return ops
}