func maximumValueSum(nums []int, k int, edges [][]int) int64 {
	base := int64(0);// Tổng ban đầu
	sumPos := int64(0);// Tổng phần tăng thêm từ những node có lợi
	cntPos := 0;// Đếm số node có lợi (delta > 0)
	minPos := int64(1e18);// delta dương nhỏ nhất
	bestNonPos := int64(-1e18);// delta âm ít tiêu cực nhất
	sawNonPos := false;

	for _, x := range nums {
		base += int64(x);
		d := int64(x^k) - int64(x);

		if d > 0 {
			cntPos++;
			sumPos += d;

			if d < minPos {
				minPos = d;
			}
		} else {
			if !sawNonPos || d > bestNonPos {
				bestNonPos = d;
				sawNonPos = true;
			}
		}
	}

	if cntPos%2 == 0 {
		return base + sumPos;
	}

	// Nếu số lượng node có lợi là lẻ, phải bỏ đi 1 hoặc bù 1 node không có lợi
	loss := minPos;
	if sawNonPos {
		if -bestNonPos < loss {
			loss = -bestNonPos;
		}
	}

	return base + sumPos - loss;
}
