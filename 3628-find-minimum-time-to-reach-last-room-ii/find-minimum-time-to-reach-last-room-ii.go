var dirs = [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}

type Item struct {
	index int; // flatten index: row * cols + col
	time  int; // current time to reach this cell
	odd   int; // 1 = move cost is 1, 2 = move cost is 2
}

type MinHeap []Item;

func (h MinHeap) Len() int           { return len(h); }
func (h MinHeap) Less(i, j int) bool { return h[i].time < h[j].time; } // min-heap by time
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i]; }
func (h *MinHeap) Push(x any)        { *h = append(*h, x.(Item)); }
func (h *MinHeap) Pop() any {
	old := *h;
	n := len(old);
	item := old[n-1];
	*h = old[:n-1];
	return item;
}

func minTimeToReach(grid [][]int) int {
	m, n := len(grid), len(grid[0]);
	h := &MinHeap{};
	heap.Init(h);

	heap.Push(h, Item{index: 0, time: 0, odd: 1});
	grid[0][0] = -1; // mark start as visited

	for h.Len() > 0 {
		curr := heap.Pop(h).(Item);
		r := curr.index / n;
		c := curr.index % n;

		for _, dir := range dirs {
			rr := r + dir[0];
			cc := c + dir[1];
			if rr < 0 || rr >= m || cc < 0 || cc >= n || grid[rr][cc] == -1 {
				continue;
			}

			nextIdx := rr*n + cc;
			newTime := max(curr.time, grid[rr][cc]) + curr.odd;
			if rr == m-1 && cc == n-1 {
				return newTime;
			}
            
			heap.Push(h, Item{index: nextIdx, time: newTime, odd: 3 - curr.odd});
			grid[rr][cc] = -1; // mark visited
		}
	}

	return -1;
}

func max(a, b int) int {
	if a > b {
		return a;
	}

	return b;
}