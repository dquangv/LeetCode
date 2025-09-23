func maxFrequencyElements(nums []int) int {
	m := make(map[int]int)

	for _, num := range nums {
		m[num]++
	}

	max := 0
	sum := 0
	for _, v := range m {
		if v > max {
			sum = v
			max = v
		} else if v == max {
			sum += v
		}
	}

	return sum
}