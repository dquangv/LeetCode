func maxBottlesDrunk(numBottles int, numExchange int) int {
	empty := numBottles
	drank := numBottles
	numBottles = 0
    
	for empty >= numExchange {
		// exchange bottles on by one while incrementing numExchange
		for empty >= numExchange {
			numBottles++
			empty -= numExchange
			numExchange++
		}

		// and drinking them again
		drank += numBottles
		empty += numBottles
		numBottles = 0
	}

	return drank
}