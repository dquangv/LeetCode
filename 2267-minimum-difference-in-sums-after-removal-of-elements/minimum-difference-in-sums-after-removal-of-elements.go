type MaxHeap []int
func (h MaxHeap) Len() int           { return len(h) }
func (h MaxHeap) Less(i, j int) bool { return h[i] > h[j] } // max-heap
func (h MaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x any)        { *h = append(*h, x.(int)) }
func (h *MaxHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

type MinHeap []int
func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x any)        { *h = append(*h, x.(int)) }
func (h *MinHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

func minimumDifference(nums []int) int64 {
	n := len(nums) / 3
	leftHeap := &MaxHeap{}
	rightHeap := &MinHeap{}
	heap.Init(leftHeap)
	heap.Init(rightHeap)

	leftSum := int64(0)
	rightSum := int64(0)
	minLeft := make([]int64, len(nums))

	// First part
	for i := 0; i < n; i++ {
		leftSum += int64(nums[i])
		heap.Push(leftHeap, nums[i])
	}
	minLeft[n-1] = leftSum

	for i := n; i < 2*n; i++ {
		leftSum += int64(nums[i])
		heap.Push(leftHeap, nums[i])
		leftSum -= int64(heap.Pop(leftHeap).(int))
		minLeft[i] = leftSum
	}

	// Right part
	for i := 3*n - 1; i >= 2*n; i-- {
		rightSum += int64(nums[i])
		heap.Push(rightHeap, nums[i])
	}
	res := minLeft[2*n-1] - rightSum

	for i := 2*n - 1; i >= n; i-- {
		rightSum += int64(nums[i])
		heap.Push(rightHeap, nums[i])
		rightSum -= int64(heap.Pop(rightHeap).(int))
		diff := minLeft[i-1] - rightSum
		if diff < res {
			res = diff
		}
	}

	return res
}
