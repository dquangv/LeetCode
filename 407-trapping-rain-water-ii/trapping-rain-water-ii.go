type Item struct {
	Height int
	Row    int
	Col    int
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].Height < pq[j].Height
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *PriorityQueue) Push(x interface{}) {
	(*pq) = append((*pq), x.(*Item))
}

func (pq *PriorityQueue) Pop() interface{} {
	top := (*pq)[len(*pq)-1]
	(*pq) = (*pq)[:len(*pq)-1]
	return top
}

func trapRainWater(heightMap [][]int) int {

	m, n := len(heightMap), len(heightMap[0])

	if m < 3 || n < 3 {
		return 0
	}

	visited := make([][]bool, m)
	for idx := range visited {
		visited[idx] = make([]bool, n)
	}

	pq := make(PriorityQueue, 0)
	heap.Init(&pq)
	for idx := 0; idx < m; idx = idx + m - 1 {
		for idx2 := 0; idx2 < n; idx2++ {
			visited[idx][idx2] = true
			heap.Push(&pq, &Item{Height: heightMap[idx][idx2], Row: idx, Col: idx2})
		}
	}

	for idx := 0; idx < n; idx = idx + n - 1 {
		for idx2 := 1; idx2 < m-1; idx2++ {
			visited[idx2][idx] = true
			heap.Push(&pq, &Item{Height: heightMap[idx2][idx], Row: idx2, Col: idx})
		}
	}

	water := 0
	for len(pq) != 0 {
		top := (heap.Pop(&pq)).(*Item)
		row, col, height := top.Row, top.Col, top.Height
		neighbours := [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}

		for _, neighbour := range neighbours {
			newRow := row + neighbour[0]
			newCol := col + neighbour[1]

			if newRow > 0 && newRow < m-1 && newCol > 0 && newCol < n-1 && !visited[newRow][newCol] {
				visited[newRow][newCol] = true
				waterToStore := height - heightMap[newRow][newCol]
				if waterToStore < 0 {
					heap.Push(&pq, &Item{Height: heightMap[newRow][newCol], Row: newRow, Col: newCol})
				} else {
					water += waterToStore
					heap.Push(&pq, &Item{Height: height, Row: newRow, Col: newCol})
				}
			}

		}
	}

	return water
}