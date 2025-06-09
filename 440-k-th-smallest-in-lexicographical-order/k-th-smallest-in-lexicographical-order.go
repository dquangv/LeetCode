func findKthNumber(n int, k int) int {
    curr := 1;
	k--; // Đã tính curr = 1 là số đầu tiên rồi

	for k > 0 {
		steps := countSteps(n, curr, curr+1);
		if steps <= k {
			// Bỏ qua cả cây con bắt đầu từ curr
			k -= steps;
			curr++;
		} else {
			// Đi sâu vào cây con curr
			k--;
			curr *= 10;
		}
	}
	return curr;
}

// Đếm số lượng số trong cây con từ prefix curr đến next (không vượt quá n)
func countSteps(n int, curr, next int) int {
	steps := 0;
	for curr <= n {
		steps += min(n+1, next) - curr;
		curr *= 10;
		next *= 10;
	}
	return steps;
}

func min(a, b int) int {
	if a < b {
		return a;
	}
	return b;
}