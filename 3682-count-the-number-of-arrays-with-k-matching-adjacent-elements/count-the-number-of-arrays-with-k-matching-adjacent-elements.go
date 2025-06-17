const mod = 1_000_000_007
const maxN = 100001

var fact [maxN]int

// Khởi tạo giai thừa nếu chưa có
func getFactorial(n int) int {
	if fact[n] != 0 {
		return fact[n]
	}
	if n == 0 {
		fact[0] = 1
		return 1
	}
	fact[n] = getFactorial(n-1) * n % mod
	return fact[n]
}

// Lũy thừa nhanh (a^b % mod)
func power(a, b int) int {
	res := 1
	base := a % mod
	for b > 0 {
		if b%2 == 1 {
			res = res * base % mod
		}
		base = base * base % mod
		b /= 2
	}
	return res
}

// Tính nghịch đảo modulo bằng Fermat: a^(mod-2) % mod
func modInverse(a int) int {
	return power(a, mod-2)
}

// C(n, k) = n! / (k! * (n-k)!)
func comb(n, k int) int {
	if k < 0 || k > n {
		return 0
	}
	return getFactorial(n) * modInverse(getFactorial(k)) % mod * modInverse(getFactorial(n-k)) % mod
}

// Hàm chính
func countGoodArrays(n, m, k int) int {
	// Số phần tử khác nhau: (n-1) - k
	diff := n - 1 - k
	return m * power(m-1, diff) % mod * comb(n-1, diff) % mod
}