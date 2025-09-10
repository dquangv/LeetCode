func minimumTeachings(n int, languages [][]int, friendships [][]int) int {
	// Convert each user's languages into a set (map[int]bool)
	userLang := make([]map[int]bool, len(languages))
	for i, l := range languages {
		userLang[i] = make(map[int]bool)
		for _, lang := range l {
			userLang[i][lang] = true
		}
	}

	dontspeak := make(map[int]bool)

	// Step 1: Identify pairs that cannot communicate
	for _, fr := range friendships {
		u, v := fr[0]-1, fr[1]-1 // convert to 0-based
		share := false
		for lang := range userLang[u] {
			if userLang[v][lang] {
				share = true
				break
			}
		}
		if !share {
			dontspeak[u] = true
			dontspeak[v] = true
		}
	}

	// If no problem, return 0
	if len(dontspeak) == 0 {
		return 0
	}

	// Step 2: Count how many in dontspeak already know each language
	langCount := make([]int, n+1)
	for f := range dontspeak {
		for lang := range userLang[f] {
			langCount[lang]++
		}
	}

	// Step 3: Find the best language
	maxKnown := 0
	for _, c := range langCount {
		if c > maxKnown {
			maxKnown = c
		}
	}

	return len(dontspeak) - maxKnown
}