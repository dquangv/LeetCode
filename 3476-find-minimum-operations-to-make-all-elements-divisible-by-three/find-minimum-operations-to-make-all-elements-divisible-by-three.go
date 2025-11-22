func minimumOperations(nums []int) int {
	ops := 0
	for _, n := range nums {
		if n%3 != 0 {
			ops++
		}
	}
    
	return ops
}