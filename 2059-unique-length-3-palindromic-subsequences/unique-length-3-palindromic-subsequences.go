func countPalindromicSubsequence(s string) int {
	count := 0
	seen := make(map[rune]bool)
	for _, char := range s {
		if !seen[char] {
			seen[char] = true
			first := findFirstOccurrence(s, char)
			last := findLastOccurrence(s, char)
			if first < last {
				uniqueChars := getUniqueChars(s[first+1 : last])
				count += len(uniqueChars)
			}
		}
	}

	return count
}

func findFirstOccurrence(s string, char rune) int {
	for i, c := range s {
		if c == char {
			return i
		}
	}

	return -1
}

func findLastOccurrence(s string, char rune) int {
	for i := len(s) - 1; i >= 0; i-- {
		if rune(s[i]) == char {
			return i
		}
	}

	return -1
}

func getUniqueChars(s string) map[rune]bool {
	unique := make(map[rune]bool)
	for _, c := range s {
		unique[c] = true
	}
    
	return unique
}