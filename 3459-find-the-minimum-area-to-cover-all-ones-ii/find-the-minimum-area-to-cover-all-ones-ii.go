type key struct {
	i1, j1, i2, j2, k int
}

func minimumSum(grid [][]int) int {
	memo := make(map[key]int)
	m, n := len(grid), len(grid[0])

	// Helper: area of bounding rectangle for all 1s in subgrid
	var getOne func(int, int, int, int) int
	getOne = func(i1, j1, i2, j2 int) int {
		minx, maxx := math.MaxInt32, math.MinInt32
		miny, maxy := math.MaxInt32, math.MinInt32

		for i := i1; i <= i2; i++ {
			for j := j1; j <= j2; j++ {
				if grid[i][j] == 1 {
					if i < minx {
						minx = i
					}
					if i > maxx {
						maxx = i
					}
					if j < miny {
						miny = j
					}
					if j > maxy {
						maxy = j
					}
				}
			}
		}
		if minx == math.MaxInt32 {
			return 0
		}
		return (maxx - minx + 1) * (maxy - miny + 1)
	}

	// Recursive with memo
	var getNext func(int, int, int, int, int) int
	getNext = func(i1, j1, i2, j2, k int) int {
		kd := key{i1, j1, i2, j2, k}
		if val, ok := memo[kd]; ok {
			return val
		}

		res := math.MaxInt32
		if k == 1 {
			res = getOne(i1, j1, i2, j2)
		} else if k == 2 {
			// Horizontal splits
			for i := i1; i < i2; i++ {
				a := getNext(i1, j1, i, j2, 1) + getNext(i+1, j1, i2, j2, 1)
				if a < res {
					res = a
				}
			}
			// Vertical splits
			for j := j1; j < j2; j++ {
				a := getNext(i1, j1, i2, j, 1) + getNext(i1, j+1, i2, j2, 1)
				if a < res {
					res = a
				}
			}
		} else if k == 3 {
			// Horizontal splits
			for i := i1; i < i2; i++ {
				a := getNext(i1, j1, i, j2, 1) + getNext(i+1, j1, i2, j2, 2)
				if a < res {
					res = a
				}
				b := getNext(i1, j1, i, j2, 2) + getNext(i+1, j1, i2, j2, 1)
				if b < res {
					res = b
				}
			}
			// Vertical splits
			for j := j1; j < j2; j++ {
				a := getNext(i1, j1, i2, j, 1) + getNext(i1, j+1, i2, j2, 2)
				if a < res {
					res = a
				}
				b := getNext(i1, j1, i2, j, 2) + getNext(i1, j+1, i2, j2, 1)
				if b < res {
					res = b
				}
			}
		}

		memo[kd] = res
		return res
	}

	return getNext(0, 0, m-1, n-1, 3)
}