func swimInWater(grid [][]int) int {
	n := len(grid)
	pq := PQ{}

	visited := make([][]bool, n)
	for i := 0; i < len(visited); i++ {
		visited[i] = make([]bool, n)
	}

	heap.Init(&pq)
	heap.Push(&pq, Node{grid[0][0], 0, 0})
	visited[0][0] = true

	dir := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

	currMax := 0
	for {
		node := heap.Pop(&pq).(Node)
		val, x, y := node.Val, node.X, node.Y
		currMax = max(val, currMax)
		if x == n-1 && y == n-1 {
			return currMax
		}

		for i := 0; i < len(dir); i++ {
			x1, y1 := x+dir[i][0], y+dir[i][1]
			if x1 < 0 || y1 < 0 || x1 >= n || y1 >= n || visited[x1][y1] {
				continue
			}

			visited[x1][y1] = true
			heap.Push(&pq, Node{grid[x1][y1], x1, y1})
		}
	}

	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type Node struct {
	Val int
	X   int
	Y   int
}

type PQ []Node

func (pq PQ) Less(i, j int) bool { return pq[i].Val < pq[j].Val }

func (pq PQ) Len() int { return len(pq) }

func (pq PQ) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *PQ) Push(x interface{}) {
	*pq = append(*pq, x.(Node))
}

func (pq *PQ) Pop() interface{} {
	v := (*pq)[len(*pq)-1]
	*pq = (*pq)[:len(*pq)-1]
	return v
}