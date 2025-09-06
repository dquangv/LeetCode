func minOperations(queries [][]int) int64 {
	var ans int64 = 0

	for _, q := range queries {
		l, r := int64(q[0]), int64(q[1])
		var S int64 = 0
		var dMax int64 = 0

		for k := 1; k < 32; k++ {
			low := int64(1) << (k - 1)
			high := (int64(1) << k) - 1

			if low > r {
				break
			}

			a := max64(l, low)
			b := min64(r, high)
			if a > b {
				continue
			}

			cnt := b - a + 1
			stepsForK := int64((k + 1) / 2)

			S += cnt * stepsForK
			if stepsForK > dMax {
				dMax = stepsForK
			}
		}

		ops := dMax
		if (S+1)/2 > ops {
			ops = (S + 1) / 2
		}
		ans += ops
	}
	return ans
}

func min64(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

func max64(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}