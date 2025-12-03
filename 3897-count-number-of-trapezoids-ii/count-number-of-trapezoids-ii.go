func countTrapezoids(points [][]int) int {
	t := make(map[int]map[int]int)
	v := make(map[int]map[int]int)
	n := len(points)

	add := func(mp map[int]map[int]int, key int, des int) {
		if _, ok := mp[key]; !ok {
			mp[key] = make(map[int]int)
		}
		mp[key][des]++
	}

	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			dx := x2 - x1
			dy := y2 - y1

			if dx < 0 || (dx == 0 && dy < 0) {
				dx = -dx
				dy = -dy
			}

			g := gcd(dx, abs(dy))
			sx := dx / g
			sy := dy / g

			des := sx*y1 - sy*x1

			key1 := (sx << 12) | (sy + 2000)
			key2 := (dx << 12) | (dy + 2000)

			add(t, key1, des)
			add(v, key2, des)
		}
	}

	return count(t) - count(v)/2
}

func gcd(a, b int) int {
	a = abs(a)
	b = abs(b)
	for b != 0 {
		a, b = b, a%b
	}

	return a
}

func abs(a int) int {
	if a < 0 {
		return -a
	}

	return a
}

func count(mp map[int]map[int]int) int {
	ans := 0
	for _, inner := range mp {
		total := 0
		for _, val := range inner {
			total += val
		}
		rem := total
		for _, val := range inner {
			rem -= val
			ans += val * rem
		}
	}
    
	return ans
}