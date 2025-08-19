func zeroFilledSubarray(nums []int) int64 {
    var count int64 = 0 // current streak of zeros
	var total int64 = 0

	for _, v := range nums {
		if v == 0 {
			count++        // extend streak
			total += count // add new subarrays ending here
		} else {
			count = 0 // reset streak if not zero
		}
	}

	return total
}