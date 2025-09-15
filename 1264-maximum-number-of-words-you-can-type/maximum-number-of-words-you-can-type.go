func canBeTypedWords(text string, brokenLetters string) int {
	broken := make(map[rune]bool)
	for _, c := range brokenLetters {
		broken[c] = true
	}

	count := 0
	words := strings.Split(text, " ")
	for _, w := range words {
		valid := true
		for _, c := range w {
			if broken[c] {
				valid = false
				break
			}
		}

		if valid {
			count++
		}
	}
    
	return count
}