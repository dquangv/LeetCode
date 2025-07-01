func possibleStringCount(word string) int {
    n := len(word)
	count := n

	for i := 1; i < n; i++ {
		if word[i] != word[i-1] {
			count-- // không cùng ký tự trước -> không tính là vị trí có thể giảm
		}
	}

	return count
}