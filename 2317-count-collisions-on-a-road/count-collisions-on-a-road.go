func countCollisions(s string) int {
	res := 0
	rcnt := 0
	scnt := 0
	for i := range s {
		if s[i] == 'R' {
			rcnt++
			continue
		}

		if s[i] == 'L' && rcnt+scnt == 0 { 
			continue
		}

		if s[i] == 'L' { 
			res++
		}

		res += rcnt
		rcnt = 0
		scnt |= 1
	}
	return res
}