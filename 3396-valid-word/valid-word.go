func isValid(word string) bool {
	if len(word) < 3 {
		return false
	}

	vowels := 0
	consonants := 0

	for _, ch := range word {
		if !unicode.IsLetter(ch) && !unicode.IsDigit(ch) {
			return false
		}

		if unicode.IsLetter(ch) {
			lower := unicode.ToLower(ch)
			if strings.ContainsRune("aeiou", lower) {
				vowels++
			} else {
				consonants++
			}
		}
	}

	return vowels > 0 && consonants > 0
}