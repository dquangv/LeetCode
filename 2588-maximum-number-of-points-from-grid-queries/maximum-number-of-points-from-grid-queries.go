var directions = [][]int{
	{1, 0}, {-1, 0}, {0, 1}, {0, -1},
}

type Cell struct {
	row, col, maxValue int
}

type MinHeap []Cell

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i].maxValue < h[j].maxValue }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(Cell))
}

func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func maxPoints(grid [][]int, queries []int) []int {
	rowCount, colCount := len(grid), len(grid[0])
	totalCells := rowCount * colCount
	maxPointsForThreshold := make([]int, totalCells+1)

	minValueToReach := make([][]int, rowCount)
	for i := range minValueToReach {
		minValueToReach[i] = make([]int, colCount)

		for j := range minValueToReach[i] {
			minValueToReach[i][j] = 1<<31 - 1
		}
	}
	minValueToReach[0][0] = grid[0][0]

	minHeap := &MinHeap{}
	heap.Init(minHeap)
	heap.Push(minHeap, Cell{0, 0, grid[0][0]})
	visitedCells := 0

	for minHeap.Len() > 0 {
		current := heap.Pop(minHeap).(Cell)
		visitedCells++
		maxPointsForThreshold[visitedCells] = current.maxValue

		for _, dir := range directions {
			newRow, newCol := current.row+dir[0], current.col+dir[1]

			if newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount &&
				minValueToReach[newRow][newCol] == (1<<31 - 1) {
				minValueToReach[newRow][newCol] = max(current.maxValue, grid[newRow][newCol])
				heap.Push(minHeap, Cell{newRow, newCol, minValueToReach[newRow][newCol]})
			}
		}
	}

	result := make([]int, len(queries))
	for i, threshold := range queries {
		left, right := 0, totalCells
		for left < right {
			mid := (left + right + 1) >> 1
			if maxPointsForThreshold[mid] < threshold {
				left = mid
			} else {
				right = mid - 1
			}
		}
		result[i] = left
	}

	return result
}