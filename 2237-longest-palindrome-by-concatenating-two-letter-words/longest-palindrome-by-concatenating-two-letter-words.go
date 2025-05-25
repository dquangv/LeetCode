func longestPalindrome(words []string) int {
	count := [26][26]int{};

	for _, word := range words {
		a := word[0] - 'a';
		b := word[1] - 'a';
		count[a][b]++;
	}

	length := 0;
	hasCenter := false;

	for i := 0; i < 26; i++ {
		for j := 0; j < 26; j++ {
			if i == j {
				length += (count[i][i] / 2) * 4;
				if count[i][i]%2 == 1 {
					hasCenter = true;
				}
			} else if i < j {
				pairs := min(count[i][j], count[j][i]);
				length += pairs * 4;
			}
		}
	}

	if hasCenter {
		length += 2;
	}

	return length;
}

func min(a, b int) int {
	if a < b {
		return a;
	}
	return b;
}
