func smallestRepunitDivByK(k int) int {
	if 1%k == 0 {
		return 1
	}

	for n, i := 1, 1; i <= k; n, i = ((n*10 + 1) % k), i+1 {
		if n == 0 {
			return i
		}
	}
    
	return -1
}