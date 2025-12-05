func countPartitions(nums []int) int {
	rightSum, leftSum, result := 0, 0, 0
	for _, num := range nums {
		rightSum += num
	}

	for _, num := range nums[:len(nums)-1] {
		leftSum += num
		rightSum -= num
		if (leftSum-rightSum)%2 == 0 {
			result++
		}
	}
    
	return result
}