func snakesAndLadders(board [][]int) int {
	n := len(board);
	target := n * n;

	// Bước 1: Chuyển ma trận 2D thành mảng 1 chiều theo kiểu Boustrophedon
	flattened := make([]int, target+1); // từ 1 đến n^2
	index := 1;
	leftToRight := true;

	for row := n - 1; row >= 0; row-- {
		if leftToRight {
			for col := 0; col < n; col++ {
				flattened[index] = board[row][col];
				index++;
			}
		} else {
			for col := n - 1; col >= 0; col-- {
				flattened[index] = board[row][col];
				index++;
			}
		}
		leftToRight = !leftToRight;
	}

	// Bước 2: BFS với hàng đợi
	queue := make([]int, target); // dùng như hàng đợi vòng
	head, tail := 0, 0;
	queue[tail] = 1;
	tail++;

	steps := make([]int, target+1); // steps[i] = số bước để đến ô i
	steps[1] = 1;

	for head != tail {
		pos := queue[head];
		head = (head + 1) % target;

		// Nếu trong 1 roll tới được đích thì trả kết quả luôn
		if pos+6 >= target {
			return steps[pos];
		}

		maxNeutral := 0;

		for roll := 6; roll >= 1; roll-- {
			next := pos + roll
			if next > target {
				continue;
			}

			if flattened[next] != -1 {
				// Có snake hoặc ladder
				next = flattened[next];
				if next == target {
					return steps[pos];
				}
			} else {
				// Không có snake/ladder
				if roll < maxNeutral {
					continue;
				}
				maxNeutral = roll;
			}

			if steps[next] == 0 {
				steps[next] = steps[pos] + 1;
				queue[tail] = next;
				tail = (tail + 1) % target;

				// Nếu head == tail thì hàng đợi bị tràn
				if head == tail {
					return 0;
				}
			}
		}
	}

	return -1; // Không thể đến đích
}
