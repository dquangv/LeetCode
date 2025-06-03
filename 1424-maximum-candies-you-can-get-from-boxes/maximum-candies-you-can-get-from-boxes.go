func maxCandies(status []int, candies []int, keys [][]int, containedBoxes [][]int, initialBoxes []int) int {
    n := len(status);
	hasKey := make([]bool, n);     // Đánh dấu đã có chìa khóa của hộp nào
	hasBox := make([]bool, n);     // Đánh dấu đang sở hữu hộp nào
	opened := make([]bool, n);     // Đánh dấu hộp nào đã được mở
	queue := []int{};              // Queue dùng BFS để duyệt hộp
	total := 0;                    // Tổng số kẹo nhặt được

	// Khởi tạo: đánh dấu hộp ban đầu, nếu hộp mở thì thêm vào queue
	for _, box := range initialBoxes {
		hasBox[box] = true;
		if status[box] == 1 {
			queue = append(queue, box);
			opened[box] = true;
		}
	}

	for len(queue) > 0 {
		// Pop hộp đầu tiên ra khỏi queue
		curr := queue[0];
		queue = queue[1:];

		// Nhặt kẹo
		total += candies[curr];

		// Nhặt chìa khóa trong hộp này
		for _, key := range keys[curr] {
			hasKey[key] = true;
			// Nếu trước đó đã có hộp này nhưng chưa mở, giờ có key thì mở luôn
			if hasBox[key] && !opened[key] && status[key] == 0 {
				queue = append(queue, key);
				opened[key] = true;
			}
		}

		// Nhặt các hộp chứa bên trong
		for _, box := range containedBoxes[curr] {
			hasBox[box] = true;
			// Nếu hộp mở sẵn hoặc ta có chìa rồi thì mở luôn
			if status[box] == 1 || hasKey[box] {
				if !opened[box] {
					queue = append(queue, box);
					opened[box] = true;
				}
			}
		}
	}

	return total;
}