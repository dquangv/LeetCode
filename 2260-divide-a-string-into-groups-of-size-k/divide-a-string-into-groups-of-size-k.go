func divideString(s string, k int, fill byte) []string {
	var result []string
	for i := 0; i < len(s); i += k {
		end := i + k
		if end > len(s) {
			end = len(s)
		}
		group := s[i:end]

		if len(group) < k {
			for len(group) < k {
				group += string(fill)
			}
		}

		result = append(result, group)
	}
	return result
}