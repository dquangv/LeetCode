func longestSubsequenceRepeatedK(s string, k int) string {
    freq := make([]int, 26)
	for _, c := range s {
		freq[c-'a']++
	}

	valid := []byte{}
	for i := 25; i >= 0; i-- {
		if freq[i] >= k {
			valid = append(valid, byte('a'+i))
		}
	}
	if len(valid) == 0 {
		return ""
	}

	filtered := []byte{}
	for i := 0; i < len(s); i++ {
		if freq[s[i]-'a'] >= k {
			filtered = append(filtered, s[i])
		}
	}

	n := len(filtered)
	maxLen := n / k
	ans := ""

	var repeatK func([]byte, string, int) bool
	repeatK = func(s []byte, t string, k int) bool {
		j := 0
		m := len(t)
		for _, ch := range s {
			if j < m && ch == t[j] {
				j++
			}
			if j == m {
				k--
				j = 0
				if k == 0 {
					return true
				}
			}
		}
		return k == 0
	}

	var dfs func(string)
	dfs = func(t string) {
		if len(t) > maxLen {
			return
		}
		if t != "" && !repeatK(filtered, t, k) {
			return
		}
		if len(t) > len(ans) || (len(t) == len(ans) && t > ans) {
			ans = t
		}
		for _, c := range valid {
			dfs(t + string(c))
		}
	}
	dfs("")
	return ans
}