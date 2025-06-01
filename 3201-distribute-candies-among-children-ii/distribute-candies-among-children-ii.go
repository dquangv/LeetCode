func distributeCandies(n int, m int) int64 {
	// Bước 1: Tính tổng số bộ (a, b, c) không âm thỏa a + b + c = n
	// Công thức tổ hợp: C(n + 2, 2) = (n + 2)(n + 1)/2
	res := int64(n+2) * int64(n+1) / 2;

	// Bước 2: Áp dụng nguyên lý bao gồm - loại trừ để loại bỏ trường hợp có biến > m
	for i := 1; i <= 3; i++ {
		// Tổng còn lại sau khi trừ i biến vượt quá m
		rem := int64(n) - int64(i)*(int64(m)+1);
		if rem < 0 {
			break;
		}

		// Tính số bộ không âm có tổng = rem
		val := (rem + 2) * (rem + 1) / 2;

		// Số cách chọn i biến vượt limit: C(3, i) = 3 (i=1,2) hoặc 1 (i=3)
		c := int64(1);
		if i < 3 {
			c = 3;
		}

		// Bao gồm - loại trừ
		if i%2 != 0 {
			res -= c * val;
		} else {
			res += c * val;
		}
	}

	return res;
}