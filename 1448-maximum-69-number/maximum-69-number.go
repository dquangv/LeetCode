func maximum69Number (num int) int {
    numStr := []rune(strconv.Itoa(num))
	for i := 0; i < len(numStr); i++ {
		if numStr[i] == '6' {
			numStr[i] = '9'
			break
		}
	}
	result, _ := strconv.Atoi(string(numStr))
	return result
}