// Priority Queue để lưu ký tự theo thứ tự từ điển (min-heap)
type CharHeap []byte;

func (h CharHeap) Len() int            { return len(h); }
func (h CharHeap) Less(i, j int) bool  { return h[i] < h[j]; }
func (h CharHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i]; }
func (h *CharHeap) Push(x interface{}) { *h = append(*h, x.(byte)); }
func (h *CharHeap) Pop() interface{} {
	old := *h;
	n := len(old);
	x := old[n-1];
	*h = old[:n-1];
	return x;
}

func clearStars(s string) string {
	n := len(s);
	keep := make([]bool, n); // keep[i] = true nếu giữ lại ký tự s[i]
	for i := 0; i < n; i++ {
		keep[i] = true;
	}

	h := &CharHeap{};
	heap.Init(h);

	// map ký tự -> danh sách các index (theo thứ tự xuất hiện)
	positions := make(map[byte][]int);

	for i := 0; i < n; i++ {
		ch := s[i];
		if ch == '*' {
			// Lấy ký tự nhỏ nhất
			if h.Len() == 0 {
				continue; // nếu không có ký tự nào còn lại để xóa, bỏ qua
			}
			smallest := heap.Pop(h).(byte);

			// Lấy index cuối cùng của ký tự nhỏ nhất (gần nhất với dấu *)
			idxList := positions[smallest];
			indexToRemove := idxList[len(idxList)-1];
			positions[smallest] = idxList[:len(idxList)-1];

			// Đánh dấu không giữ lại
			keep[i] = false;               // bỏ dấu '*'
			keep[indexToRemove] = false;  // bỏ ký tự nhỏ nhất trước đó
		} else {
			heap.Push(h, ch);
			positions[ch] = append(positions[ch], i);
		}
	}

	// Duyệt và xây kết quả
	result := []byte{};
	for i := 0; i < n; i++ {
		if keep[i] {
			result = append(result, s[i]);
		}
	}
	return string(result);
}
