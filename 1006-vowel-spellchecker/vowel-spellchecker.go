func spellchecker(wordlist []string, queries []string) []string {
	// Exact match set
	exact := make(map[string]bool)
	// Lowercase map
	lower := make(map[string]string)
	// Devowel map
	vow := make(map[string]string)

	// Preprocess wordlist
	for _, w := range wordlist {
		exact[w] = true
		lw := lowerIt(w)
		if _, ok := lower[lw]; !ok {
			lower[lw] = w
		}
		dw := devowel(lw)
		if _, ok := vow[dw]; !ok {
			vow[dw] = w
		}
	}

	res := make([]string, len(queries))
	for i, q := range queries {
		if exact[q] {
			// Case 1: Exact match
			res[i] = q
		} else if w, ok := lower[lowerIt(q)]; ok {
			// Case 2: Case-insensitive
			res[i] = w
		} else if w, ok := vow[devowel(lowerIt(q))]; ok {
			// Case 3: Vowel error
			res[i] = w
		} else {
			// Case 4: No match
			res[i] = ""
		}
	}

	return res
}

// Helper function: lowercase
func lowerIt(s string) string {
	return strings.ToLower(s)
}

// Helper function: replace vowels with '#'
func devowel(s string) string {
	vowels := "aeiou"
	result := []rune(s)
    
	for i, ch := range result {
		if strings.ContainsRune(vowels, ch) {
			result[i] = '#'
		}
	}

	return string(result)
}