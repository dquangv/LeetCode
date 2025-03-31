func putMarbles(weights []int, k int) int64 {
    n := len(weights);

	if k == 1 {
		return 0;
	}

	// Tạo danh sách tổng của các cặp số liên tiếp
	pairWeights := make([]int, n-1);
	for i := 0; i < n-1; i++ {
		pairWeights[i] = weights[i] + weights[i+1];
	}

	// Sắp xếp theo thứ tự tăng dần
	sort.Ints(pairWeights);

	// Tính hiệu giữa tổng lớn nhất và nhỏ nhất
	var answer int64 = 0
	for i := 0; i < k-1; i++ {
		answer += int64(pairWeights[n-2-i] - pairWeights[i]);
	}

	return answer;
}