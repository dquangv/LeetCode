func findLexSmallestString(s string, a int, b int) string {
	vis := map[string]bool{s: true}
	smallest := s
	q := []string{s}

	for len(q) > 0 {
		cur := q[0]
		q = q[1:]
		if cur < smallest {
			smallest = cur
		}

		runes := []rune(cur)
		for i := 1; i < len(runes); i += 2 {
			runes[i] = rune(((int(runes[i]-'0') + a) % 10) + '0')
		}
		added := string(runes)
		if !vis[added] {
			vis[added] = true
			q = append(q, added)
		}

		rotated := cur[len(cur)-b:] + cur[:len(cur)-b]
		if !vis[rotated] {
			vis[rotated] = true
			q = append(q, rotated)
		}
	}

	return smallest
}