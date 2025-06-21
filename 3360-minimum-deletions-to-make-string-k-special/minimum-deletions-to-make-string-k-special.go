func minimumDeletions(word string, k int) int {
	freq := make([]int, 26)

	// Đếm tần suất mỗi ký tự trong chuỗi
	for i := 0; i < len(word); i++ {
		freq[word[i]-'a']++
	}

	minDel := int(1e9)

	// Chọn mỗi ký tự làm "base", xem như chuẩn để so sánh
	for base := 0; base < 26; base++ {
		if freq[base] == 0 {
			continue
		}
		del := 0
		baseFreq := freq[base]

		for comp := 0; comp < 26; comp++ {
			if freq[comp] == 0 || comp == base {
				continue
			}

			compFreq := freq[comp]

			if compFreq < baseFreq {
				// Nếu nhỏ hơn base, xóa toàn bộ
				del += compFreq
			} else if compFreq-baseFreq > k {
				// Nếu lớn hơn base + k, xóa phần dư
				del += compFreq - baseFreq - k
			}
		}

		if del < minDel {
			minDel = del
		}
	}

	return minDel
}