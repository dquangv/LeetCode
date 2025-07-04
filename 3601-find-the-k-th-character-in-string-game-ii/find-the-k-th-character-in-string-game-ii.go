func kthCharacter(k int64, operations []int) byte {
    shifts := 0

	for k != 1 {
		// Tìm vị trí bit cao nhất i sao cho 2^i <= k
		i := bits.Len64(uint64(k)) - 1

		// Nếu k là đúng lũy thừa của 2, giảm i đi 1
		if (1 << i) == k {
			i--
		}

		k -= (1 << i)

		// Nếu phép biến đổi tại bước i là loại 1, tăng shift
		if operations[i] == 1 {
			shifts++
		}
	}

	// Trả về ký tự kết quả sau khi shift từ 'a'
	return byte((shifts % 26) + 'a')
}