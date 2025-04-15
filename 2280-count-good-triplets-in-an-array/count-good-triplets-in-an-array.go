type FenwickTree struct {
	tree []int
}

func NewFenwickTree(size int) *FenwickTree {
	return &FenwickTree{tree: make([]int, size+1)}
}

func (ft *FenwickTree) Update(index, delta int) {
	index++
	for index < len(ft.tree) {
		ft.tree[index] += delta
		index += index & -index
	}
}

func (ft *FenwickTree) Query(index int) int {
	index++
	res := 0
	for index > 0 {
		res += ft.tree[index]
		index -= index & -index
	}
	return res
}

func goodTriplets(nums1, nums2 []int) int64 {
	n := len(nums1)
	pos2 := make([]int, n)
	mapping := make([]int, n)

	// Xây dựng ánh xạ số -> vị trí trong nums2
	for i := 0; i < n; i++ {
		pos2[nums2[i]] = i
	}

	// Tạo mảng vị trí nums1 theo thứ tự nums2
	for i := 0; i < n; i++ {
		mapping[pos2[nums1[i]]] = i
	}

	tree := NewFenwickTree(n)
	var res int64 = 0

	for value := 0; value < n; value++ {
		pos := mapping[value]
		left := tree.Query(pos)
		tree.Update(pos, 1)
		right := (n - 1 - pos) - (value - left)
		res += int64(left * right)
	}

	return res
}
