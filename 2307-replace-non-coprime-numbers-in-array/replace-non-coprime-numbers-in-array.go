func replaceNonCoprimes(nums []int) []int {
	res := []int{}

	for _, num := range nums {
		// Merge as long as last element and num are non-coprime
		for len(res) > 0 && gcd(res[len(res)-1], num) > 1 {
			num = lcm(res[len(res)-1], num)
			res = res[:len(res)-1] // pop last element
		}
		res = append(res, num)
	}

	return res
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
    
	return a
}

// lcm computes the least common multiple
func lcm(a, b int) int {
	return a / gcd(a, b) * b
}