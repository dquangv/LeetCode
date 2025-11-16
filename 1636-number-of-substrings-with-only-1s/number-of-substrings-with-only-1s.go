func numSub(s string) int {
	r := 0
	res := 0
	for i, _ := range s {
		if r == 0 {
			if s[i] == '1' {
				res++
				r++
			}
		} else {
			if s[i] == '1' {
				r++
				res += r
			} else {
				r = 0
			}
		}
	}
	return res % int(math.Pow10(9)+7)
}