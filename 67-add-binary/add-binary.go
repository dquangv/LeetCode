func addBinary(a string, b string) string {
	var sb strings.Builder;
	carry := 0;
	i := len(a) - 1;
	j := len(b) - 1;

	// Lặp khi còn ký tự trong a hoặc b hoặc còn dư carry
	for i >= 0 || j >= 0 || carry == 1 {
		if i >= 0 {
			carry += int(a[i] - '0'); // Lấy giá trị nhị phân của ký tự a
			i--;
		}

		if j >= 0 {
			carry += int(b[j] - '0'); // Lấy giá trị nhị phân của ký tự b
			j--;
		}

		sb.WriteByte(byte(carry%2) + '0'); // Thêm bit hiện tại vào chuỗi kết quả
		carry /= 2;                        // Tính toán giá trị nhớ (carry)
	}

	// Chuỗi đang ở dạng ngược, cần đảo lại
	result := sb.String();
	// Đảo chuỗi kết quả
	runes := []rune(result);
	for i, n := 0, len(runes); i < n/2; i++ {
		runes[i], runes[n-1-i] = runes[n-1-i], runes[i];
	}

	return string(runes);
}