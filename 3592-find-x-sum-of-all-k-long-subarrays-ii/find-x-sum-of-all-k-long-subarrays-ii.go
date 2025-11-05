type freq struct {
	val   int32
	count int32
	index int32
	topx  bool
}

func isLess(f1, f2 *freq) bool {
	if f1.count == f2.count {
		return f1.val < f2.val
	}

	return f1.count < f2.count
}

type (
	pqMin = [0]bool
	pqMax = [1]bool
)

type pqKinds interface {
	pqMin | pqMax
}

func isMax[K pqKinds]() bool {
	var kind K
	return 0 < len(kind)
}

type pqFreqs[K pqKinds] []*freq

func (pq *pqFreqs[_]) Len() int {
	return len(*pq)
}

func (pq *pqFreqs[K]) Less(i, j int) bool {
	s := *pq
	if isMax[K]() {
		return isLess(s[j], s[i])
	}

	return isLess(s[i], s[j])
}

func (pq *pqFreqs[_]) Swap(i, j int) {
	s := *pq
	s[i], s[j] = s[j], s[i]
	s[i].index = int32(i)
	s[j].index = int32(j)
}

func (pq *pqFreqs[_]) Push(x any) {
	f := x.(*freq)
	f.index = int32(len(*pq))
	*pq = append(*pq, f)
}

func (pq *pqFreqs[_]) Pop() any {
	s := *pq
	l := len(s) - 1
	result := s[l]
	*pq = s[:l]
	return result
}

type xsum struct {
	x     int
	sum   int64
	freqs map[int32]*freq
	topx  pqFreqs[pqMin]
	other pqFreqs[pqMax]
}

func newXSum(x int) *xsum {
	return &xsum{x: x, freqs: make(map[int32]*freq)}
}

func (s *xsum) removeFromTopX(f *freq) {
	s.sum -= int64(f.val) * int64(f.count)
	f.topx = false
	heap.Remove(&s.topx, int(f.index))
}

func (s *xsum) tryToRemoveFromOther(f *freq) {
	if 0 < f.count {
		heap.Remove(&s.other, int(f.index))
	}
}

func (s *xsum) tryToRemove(f *freq) {
	if f.topx {
		s.removeFromTopX(f)
	} else {
		s.tryToRemoveFromOther(f)
	}
}

func (s *xsum) pushToTopX(f *freq) {
	f.topx = true
	s.sum += int64(f.val) * int64(f.count)
	heap.Push(&s.topx, f)
}

func (s *xsum) tryToPushToOther(f *freq) {
	if 0 < f.count {
		f.topx = false
		heap.Push(&s.other, f)
	}
}

func (s *xsum) tryToPropagate(f *freq) {
	s.tryToPushToOther(f)

	if 0 < len(s.other) && s.x <= len(s.topx) && isLess(s.topx[0], s.other[0]) {
		f = s.topx[0]
		s.removeFromTopX(f)
		s.tryToPushToOther(f)
	}

	if 0 < len(s.other) && len(s.topx) < s.x {
		f = s.other[0]
		s.tryToRemoveFromOther(f)
		s.pushToTopX(f)
	}
}

func (s *xsum) push(n int) {
	val := int32(n)
	f := s.freqs[val]
	if f == nil {
		f = &freq{val: val}
		s.freqs[val] = f
	} else {
		s.tryToRemove(f)
	}

	f.count++
	s.tryToPropagate(f)
}

func (s *xsum) pop(n int) {
	val := int32(n)
	f := s.freqs[val]
	s.tryToRemove(f)
	f.count--
	s.tryToPropagate(f)
}

func findXSum(nums []int, k int, x int) []int64 {
	s := newXSum(x)
	for _, n := range nums[:k] {
		s.push(n)
	}

	result := make([]int64, len(nums)-k+1)
	result[0] = s.sum
	for i := k; i < len(nums); i++ {
		n1, n2 := nums[i-k], nums[i]
		if n1 != n2 {
			s.pop(n1)
			s.push(n2)
		}
		result[i-k+1] = s.sum
	}

	return result
}