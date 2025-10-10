func maximumEnergy(energy []int, k int) int {
	res := math.MinInt

	for o := range k {
		cur := 0

		for i := o; i < len(energy); i += k {
			cur = max(cur, 0) + energy[i]
		}

		res = max(res, cur)
	}

	return res
}