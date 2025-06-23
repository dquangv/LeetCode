func kMirror(k int, n int) int64 {
	var sum int64 = 0
	for length := int64(1); n > 0; length *= 10 {
		// Tạo các số palindrome lẻ
		for i := length; n > 0 && i < length*10; i++ {
			p := createPalindrome(i, true)
			if isPalindrome(p, k) {
				sum += p
				n--
			}
		}
		// Tạo các số palindrome chẵn
		for i := length; n > 0 && i < length*10; i++ {
			p := createPalindrome(i, false)
			if isPalindrome(p, k) {
				sum += p
				n--
			}
		}
	}
	return sum
}

func createPalindrome(num int64, odd bool) int64 {
	x := num
	if odd {
		x /= 10
	}
	for x > 0 {
		num = num*10 + x%10
		x /= 10
	}
	return num
}

func isPalindrome(num int64, base int) bool {
	digits := []int{}
	for num > 0 {
		digits = append(digits, int(num%int64(base)))
		num /= int64(base)
	}
	for i, j := 0, len(digits)-1; i < j; i, j = i+1, j-1 {
		if digits[i] != digits[j] {
			return false
		}
	}
	return true
}
