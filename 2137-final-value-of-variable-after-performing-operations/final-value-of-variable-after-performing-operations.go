func finalValueAfterOperations(operations []string) int {
	var sol int = 0
	for _, operation := range operations {
		sol = perform(sol, operation)
	}

	return sol
}

func perform(val int, op string) int {
	switch op {
	case "--X", "X--":
		return val - 1
	case "++X", "X++":
		return val + 1
	}
	return val
}