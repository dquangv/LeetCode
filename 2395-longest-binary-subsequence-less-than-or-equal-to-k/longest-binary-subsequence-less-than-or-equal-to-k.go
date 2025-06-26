func longestSubsequence(s string, k int) int {
    sum := 0
	length := 0
	maxBits := int(math.Log2(float64(k))) + 1

	n := len(s)
	for i := 0; i < n; i++ {
		bit := s[n-1-i] // duyệt từ phải sang trái
		if bit == '1' {
			if i < maxBits && sum+(1<<i) <= k {
				sum += 1 << i
				length++
			}
		} else {
			length++
		}
	}

	return length
}