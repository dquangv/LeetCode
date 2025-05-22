type MaxHeap []int

func (h MaxHeap) Len() int            { return len(h) }
func (h MaxHeap) Less(i, j int) bool  { return h[i] > h[j] } // max heap
func (h MaxHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x interface{}) { *h = append(*h, x.(int)) }
func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

func maxRemoval(nums []int, queries [][]int) int {
	n := len(nums)
	qEnd := make([][]int, n)
	for _, q := range queries {
		l, r := q[0], q[1]
		qEnd[l] = append(qEnd[l], r)
	}

	cntQ := make([]int, n+1)
	dec := 0
	pq := &MaxHeap{}
	heap.Init(pq)

	for i := 0; i < n; i++ {
		dec += cntQ[i]

		// Thêm các truy vấn bắt đầu tại i
		for _, r := range qEnd[i] {
			heap.Push(pq, r)
		}

		x := nums[i]
		for x > dec && pq.Len() > 0 && (*pq)[0] >= i {
			k := heap.Pop(pq).(int)
			cntQ[k+1]--
			dec++
		}

		if x > dec {
			return -1
		}
	}

	return pq.Len()
}