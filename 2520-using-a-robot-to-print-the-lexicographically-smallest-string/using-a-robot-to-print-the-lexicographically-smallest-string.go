func robotWithString(s string) string {
	// Hàm phụ để tìm ký tự nhỏ nhất còn lại dựa vào tần suất
	smallestRemainingChar := func(freq []int) byte {
		for i := 0; i < 26; i++ {
			if freq[i] > 0 {
				return byte('a' + i);
			}
		}
		return 'a'; // fallback
	}

	stack := []byte{};
	result := []byte{};
	freq := make([]int, 26);

	// Đếm tần suất ký tự trong chuỗi s
	for i := 0; i < len(s); i++ {
		freq[s[i]-'a']++;
	}

	// Duyệt từng ký tự trong chuỗi s
	for i := 0; i < len(s); i++ {
		ch := s[i];
		stack = append(stack, ch); // đưa ký tự vào stack (robot giữ trong t)
		freq[ch-'a']--;            // giảm số lượng ký tự còn lại

		// Nếu ký tự trên đỉnh stack nhỏ hơn hoặc bằng ký tự nhỏ nhất còn lại
		for len(stack) > 0 && stack[len(stack)-1] <= smallestRemainingChar(freq) {
			// Pop khỏi stack và thêm vào kết quả
			result = append(result, stack[len(stack)-1]);
			stack = stack[:len(stack)-1];
		}
	}

	// Ghi nốt các ký tự còn lại trong stack
	for len(stack) > 0 {
		result = append(result, stack[len(stack)-1]);
		stack = stack[:len(stack)-1];
	}

	return string(result);
}