func maxSumDivThree(nums []int) int {
	summary, one, two := 0, 0, 0
	lastOne1, lastOne2 := math.MaxInt32, math.MaxInt32
	lastTwo1, lastTwo2 := math.MaxInt32, math.MaxInt32
	for _, v := range nums {
		if v <= 0 {
			break
		}
		summary += v

		if v%3 == 1 {
			one++
			if v < lastOne1 {
				lastOne1, lastOne2 = v, lastOne1
			} else if v < lastOne2 {
				lastOne2 = v
			}
		} else if v%3 == 2 {
			two++
			if v < lastTwo1 {
				lastTwo1, lastTwo2 = v, lastTwo1
			} else if v < lastTwo2 {
				lastTwo2 = v
			}
		}
	}

	if one > two {
		switch (one - two) % 3 {
		case 1:
			summary -= min(lastOne1, lastTwo1+lastTwo2)
		case 2:
			summary -= min(lastOne1+lastOne2, lastTwo1)
		}
	} else {
		switch (two - one) % 3 {
		case 1:
			summary -= min(lastTwo1, lastOne1+lastOne2)
		case 2:
			summary -= min(lastTwo1+lastTwo2, lastOne1)
		}
	}

	return summary
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}