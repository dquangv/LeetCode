func fractionToDecimal(numerator int, denominator int) string {
	// special treatment for 0
	if numerator == 0 || denominator == 0 {
		return "0"
	}

	// process sign
	sign := 1
	if numerator < 0 {
		sign *= -1
		numerator *= -1
	}

	if denominator < 0 {
		sign *= -1
		denominator *= -1
	}

	prefix := ""
	if sign < 0 {
		prefix = "-"
	}

	integerPart := numerator / denominator
	decimalPart := numerator % denominator
	integerPartInString := strconv.Itoa(integerPart)

	// no decimal part, just return sign and integer part
	if decimalPart == 0 {
		return prefix + integerPartInString
	}

	currentDecimal := decimalPart
	order := 1
	decimalPartInBytes := make([]byte, 0)
	magic := make(map[int]int)
	for currentDecimal != 0 {
		// find infinite loop fraction
		if magic[currentDecimal] != 0 {
			repeatHeadPosition := magic[currentDecimal] - 1
			repeatPart := make([]byte, order-magic[currentDecimal])
			copy(repeatPart, decimalPartInBytes[repeatHeadPosition:])
			decimalPartInBytes = decimalPartInBytes[:repeatHeadPosition]
			decimalPartInBytes = append(decimalPartInBytes, '(')
			decimalPartInBytes = append(decimalPartInBytes, repeatPart...)
			decimalPartInBytes = append(decimalPartInBytes, ')')
			break
		}
        
		magic[currentDecimal] = order
		decimalPartInBytes = append(decimalPartInBytes, byte(currentDecimal*10/denominator)+'0')
		currentDecimal = currentDecimal * 10 % denominator
		order++
	}

	return prefix + integerPartInString + "." + string(decimalPartInBytes)
}