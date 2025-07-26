func maxSubarrays(n int, conflictingPairs [][]int) int64 {
	conflicts := make([][]int, n+1)
	for _, pair := range conflictingPairs {
		a, b := pair[0], pair[1]
		if a > b {
			a, b = b, a
		}
		conflicts[b] = append(conflicts[b], a)
	}

	restrictRemoval := make([]int64, n+1)
	leftMaxRestrict, leftSecondMaxRestrict := 0, 0
	var res int64 = 0

	for i := 1; i <= n; i++ {
		for _, ele := range conflicts[i] {
			if ele > leftMaxRestrict {
				leftSecondMaxRestrict = leftMaxRestrict
				leftMaxRestrict = ele
			} else if ele > leftSecondMaxRestrict {
				leftSecondMaxRestrict = ele
			}
		}
		res += int64(i - leftMaxRestrict)
		restrictRemoval[leftMaxRestrict] += int64(leftMaxRestrict - leftSecondMaxRestrict)
	}

	var maxRemovalVal int64 = 0
	for i := 1; i <= n; i++ {
		if restrictRemoval[i] > maxRemovalVal {
			maxRemovalVal = restrictRemoval[i]
		}
	}

	return res + maxRemovalVal
}
