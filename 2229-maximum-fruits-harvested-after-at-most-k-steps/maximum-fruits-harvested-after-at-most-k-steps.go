func maxTotalFruits(fruits [][]int, startPos int, k int) int {
	res := 0
	sum := 0
	left := 0

	for right := 0; right < len(fruits); right++ {
		sum += fruits[right][1]

		// Shrink window from the left while it's invalid
		for left <= right && !isValidRange(fruits[left][0], fruits[right][0], startPos, k) {
			sum -= fruits[left][1]
			left++
		}

		if sum > res {
			res = sum
		}
	}

	return res
}

func isValidRange(leftPos, rightPos, startPos, k int) bool {
	if rightPos <= startPos {
		return startPos-leftPos <= k
	} else if leftPos >= startPos {
		return rightPos-startPos <= k
	} else {
		left := startPos - leftPos
		right := rightPos - startPos
		if left <= right {
			return left*2 + right <= k
		}
		return right*2 + left <= k
	}
}
