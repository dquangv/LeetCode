func largestTriangleArea(points [][]int) float64 {
	size := len(points)
	var ret float64

	for i := 0; i < size-2; i++ {
		for j := i + 1; j < size-1; j++ {
			for k := 0; k < size; k++ {
				if v := cal(points[i][0], points[i][1], points[j][0], points[j][1], points[k][0], points[k][1]); v > ret {
					ret = v
				}
			}
		}
	}
    
	return ret
}

func cal(x1, y1, x2, y2, x3, y3 int) float64 {
	return math.Abs(float64(x1*y2+x2*y3+x3*y1-y1*x2-y2*x3-y3*x1)) / 2.0
}