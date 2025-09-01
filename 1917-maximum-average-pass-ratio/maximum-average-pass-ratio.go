type Class struct {
    gain  float64
    pass  float64
    total float64
}

// Priority Queue implementation
type MaxHeap []Class

func (h MaxHeap) Len() int           { return len(h) }
func (h MaxHeap) Less(i, j int) bool { return h[i].gain > h[j].gain } // max-heap
func (h MaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x interface{}) {
    *h = append(*h, x.(Class))
}
func (h *MaxHeap) Pop() interface{} {
    old := *h
    n := len(old)
    item := old[n-1]
    *h = old[:n-1]
    return item
}

func calcGain(p, t float64) float64 {
    return (p+1)/(t+1) - p/t
}

func maxAverageRatio(classes [][]int, extraStudents int) float64 {
    h := &MaxHeap{}
    heap.Init(h)

    // Initialize heap with classes
    for _, c := range classes {
        p, t := float64(c[0]), float64(c[1])
        heap.Push(h, Class{gain: calcGain(p, t), pass: p, total: t})
    }

    // Assign extra students
    for extraStudents > 0 {
        top := heap.Pop(h).(Class)
        p, t := top.pass+1, top.total+1
        heap.Push(h, Class{gain: calcGain(p, t), pass: p, total: t})
        extraStudents--
    }

    // Compute final average
    sum := 0.0
    for _, c := range *h {
        sum += c.pass / c.total
    }
    return sum / float64(len(classes))
}