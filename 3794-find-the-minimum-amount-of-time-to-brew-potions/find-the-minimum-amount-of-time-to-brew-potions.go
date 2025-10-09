func minTime(skill []int, mana []int) int64 {
	n, m := len(skill), len(mana)
	done := make([]int64, n+1)

	for j := 0; j < m; j++ {
		for i := 0; i < n; i++ {
			if done[i+1] < done[i] {
				done[i+1] = done[i]
			}
			done[i+1] += int64(skill[i]) * int64(mana[j])
		}
		for i := n - 1; i > 0; i-- {
			done[i] = done[i+1] - int64(skill[i])*int64(mana[j])
		}
	}

	return done[n]
}