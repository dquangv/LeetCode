func isOneBitCharacter(bits []int) bool {
	var one int
	for i := len(bits) - 2; i >= 0 && bits[i] != 0; i-- {
		one++
	}
    
	return one%2 == 0
}