func maxDifference(s string, k int) int {
	n := len(s);
	preSum := make([][]int, n+1);

	for i := range preSum {
		preSum[i] = make([]int, 5);
	}

	for i := 0; i < n; i++ {
		copy(preSum[i+1], preSum[i]);
		preSum[i+1][s[i]-'0']++;
	}

	maxDiff := -1 << 31 // tương đương Integer.MIN_VALUE

	for a := 0; a <= 4; a++ {
		for b := 0; b <= 4; b++ {
			if a == b {
				continue;
			}

			left, right := 0, 0;
			min := [2][2]int{};

			for i := 0; i < 2; i++ {
				for j := 0; j < 2; j++ {
					min[i][j] = 1 << 30; // tương đương Integer.MAX_VALUE / 2
				}
			}

			for right < n {
				for right-left+1 >= k &&
					preSum[right+1][a] > preSum[left][a] &&
					preSum[right+1][b] > preSum[left][b] {

					p := preSum[left][a] & 1;
					q := preSum[left][b] & 1;
					val := preSum[left][a] - preSum[left][b];
					if val < min[p][q] {
						min[p][q] = val;
					}

					left++;
				}

				x := (preSum[right+1][a] & 1) ^ 1;
				y := preSum[right+1][b] & 1;
				diff := preSum[right+1][a] - preSum[right+1][b] - min[x][y];
				if diff > maxDiff {
					maxDiff = diff;
				}
				right++;
			}
		}
	}

	return maxDiff;
}
