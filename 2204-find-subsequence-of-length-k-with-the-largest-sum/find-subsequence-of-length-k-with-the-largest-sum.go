type Pair struct {
	val int
	idx int
}

type MaxHeap []Pair

func (h MaxHeap) Len() int            { return len(h) }
func (h MaxHeap) Less(i, j int) bool  { return h[i].val > h[j].val } // Max heap
func (h MaxHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x interface{}) { *h = append(*h, x.(Pair)) }
func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	val := old[n-1]
	*h = old[:n-1]
	return val
}

func maxSubsequence(nums []int, k int) []int {
	h := &MaxHeap{}
	for i, num := range nums {
		heap.Push(h, Pair{num, i})
	}

	topK := make([]Pair, k)
	for i := 0; i < k; i++ {
		topK[i] = heap.Pop(h).(Pair)
	}

	// Sắp xếp lại theo chỉ số để giữ thứ tự subsequence
	sort.Slice(topK, func(i, j int) bool {
		return topK[i].idx < topK[j].idx
	})

	result := make([]int, k)
	for i := 0; i < k; i++ {
		result[i] = topK[i].val
	}
	return result
}