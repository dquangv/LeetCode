func makeFancyString(s string) string {
	if len(s) == 0 {
		return ""
	}

	result := make([]rune, 0, len(s))
	last := rune(s[0])
	count := 1
	result = append(result, last)

	for _, c := range s[1:] {
		if c == last {
			count++
		} else {
			last = c
			count = 1
		}

		if count <= 2 {
			result = append(result, c)
		}
	}

	return string(result)
}
