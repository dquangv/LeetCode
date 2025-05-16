func getWordsInLongestSubsequence(words []string, groups []int) []string {
	n := len(groups);
	dp := make([]int, n);
	parent := make([]int, n);

	for i := 0; i < n; i++ {
		dp[i] = 1;
		parent[i] = -1;
	}

	maxLen := 0;
	lastIndex := 0;

	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if groups[i] != groups[j] && isHammingDistance(words[i], words[j]) && dp[i] < dp[j]+1 {
				dp[i] = dp[j] + 1;
				parent[i] = j;
			}
		}

		if dp[i] > maxLen {
			maxLen = dp[i];
			lastIndex = i;
		}
	}

	// Truy vết ngược từ lastIndex
	var result []string;
	for i := lastIndex; i != -1; i = parent[i] {
		result = append(result, words[i]);
	}

	// Đảo ngược kết quả
	for i, j := 0, len(result)-1; i < j; i, j = i+1, j-1 {
		result[i], result[j] = result[j], result[i];
	}

	return result;
}

func isHammingDistance(a, b string) bool {
	if len(a) != len(b) {
		return false;
	}

	diff := 0;
	for i := 0; i < len(a); i++ {
		if a[i] != b[i] {
			diff++;
			if diff > 1 {
				return false;
			}
		}
	}
    
	return diff == 1;
}
