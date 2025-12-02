func countTrapezoids(points [][]int) int {
	const MOD = 1e9 + 7
	mp := map[int]int{}
	for _, p := range points {
		mp[p[1]]++
	}
    
	seg := []int{}
	for _, v := range mp {
		if v >= 2 {
			seg = append(seg, v*(v-1)/2%MOD)
		}
	}

	sum, ans := 0, 0
	for _, v := range seg {
		ans = (ans + v*sum) % MOD
		sum = (sum + v) % MOD
	}

	return ans
}