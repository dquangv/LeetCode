func hasSameDigits(s string) bool {
	currList := []byte(s)
	insertPos := 0
    
	for len(currList) > 2 {
		insertPos = 0
		for i := 1; i < len(currList); i++ {
			currList[insertPos] = (byte(int(currList[i-1]-'0')+int(currList[i]-'0')) % 10) + '0'
			insertPos++
		}
		currList = currList[:len(currList)-1]
	}

	return currList[0] == currList[1]
}