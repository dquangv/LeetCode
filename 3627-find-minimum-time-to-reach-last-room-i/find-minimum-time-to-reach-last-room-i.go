// Cấu trúc phần tử trong priority queue: chứa thời gian, và tọa độ (i, j)
type Cell struct {
	time int;
	i, j int;
}

// PriorityQueue cài đặt heap.Interface
type PriorityQueue []Cell;

func (pq PriorityQueue) Len() int { return len(pq); }

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].time < pq[j].time; // Sắp xếp theo thời gian tăng dần
}

func (pq PriorityQueue) Swap(i, j int) { pq[i], pq[j] = pq[j], pq[i]; }

func (pq *PriorityQueue) Push(x any) {
	*pq = append(*pq, x.(Cell));
}

func (pq *PriorityQueue) Pop() any {
	old := *pq;
	n := len(old);
	item := old[n-1];
	*pq = old[:n-1];
	return item;
}

// Hàm chính
func minTimeToReach(moveTime [][]int) int {
	n := len(moveTime);
	m := len(moveTime[0]);

	// Duyệt 4 hướng: phải, xuống, trái, lên
	d := []int{0, 1, 0, -1, 0};

	// Mảng lưu thời gian ngắn nhất đến mỗi ô
	time := make([][]int, n);
	for i := 0; i < n; i++ {
		time[i] = make([]int, m);
		for j := 0; j < m; j++ {
			time[i][j] = math.MaxInt;
		}
	}

	// Priority queue
	pq := &PriorityQueue{};
	heap.Init(pq);
	heap.Push(pq, Cell{0, 0, 0});
	time[0][0] = 0;

	// Bắt đầu Dijkstra
	for pq.Len() > 0 {
		cur := heap.Pop(pq).(Cell);

		// Nếu tới đích thì return
		if cur.i == n-1 && cur.j == m-1 {
			return cur.time;
		}

		for k := 0; k < 4; k++ {
			ni, nj := cur.i+d[k], cur.j+d[k+1];
			if ni < 0 || nj < 0 || ni >= n || nj >= m {
				continue; // Ngoài biên
			}

			// Tính thời gian cần đợi nếu tới sớm hơn moveTime
			wait := max(cur.time, moveTime[ni][nj]) + 1;

			if wait < time[ni][nj] {
				time[ni][nj] = wait;
				heap.Push(pq, Cell{wait, ni, nj});
			}
		}
	}

	return -1; // Không thể đến đích (đề không yêu cầu trường hợp này)
}

// Hàm max
func max(a, b int) int {
	if a > b {
		return a;
	}
	return b;
}