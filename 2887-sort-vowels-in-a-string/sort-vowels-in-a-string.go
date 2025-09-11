func sortVowels(s string) string {
	vowels := map[rune]bool{
		'a': true, 'e': true, 'i': true, 'o': true, 'u': true,
		'A': true, 'E': true, 'I': true, 'O': true, 'U': true,
	}

	// Step 1: Extract vowels
	var vowelList []rune
	for _, ch := range s {
		if vowels[ch] {
			vowelList = append(vowelList, ch)
		}
	}

	// Step 2: Sort vowels by ASCII
	sort.Slice(vowelList, func(i, j int) bool { return vowelList[i] < vowelList[j] })

	// Step 3: Replace vowels with sorted ones
	res := []rune(s)
	idx := 0
	for i, ch := range res {
		if vowels[ch] {
			res[i] = vowelList[idx]
			idx++
		}
	}

	// Step 4: Return result
	return string(res)
}