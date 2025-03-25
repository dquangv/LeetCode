func checkValidCuts(n int, rectangles [][]int) bool {
	x := make([][]int, len(rectangles))
	y := make([][]int, len(rectangles))

	for i := range rectangles {
		x[i] = []int{rectangles[i][0], rectangles[i][2]}
		y[i] = []int{rectangles[i][1], rectangles[i][3]}
	}

	return checkNotOverlap(x) || checkNotOverlap(y)
}

func checkNotOverlap(rectangles [][]int) bool {
	sort.Slice(rectangles, func(i, j int) bool {
		return rectangles[i][0] < rectangles[j][0]
	})

	sections := 0
	maxEnd := rectangles[0][1]

	for _, rect := range rectangles {
		if maxEnd <= rect[0] {
			sections++
		}
		maxEnd = max(maxEnd, rect[1])
	}

	return sections >= 2
}