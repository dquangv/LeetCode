func maxTaskAssign(tasks []int, workers []int, pills int, strength int) int {
    sort.Ints(tasks);
	sort.Ints(workers);

	l, r := 0, min(len(tasks), len(workers));

	for l < r {
		mid := (l + r + 1) / 2;
		if doable(tasks, workers, mid, pills, strength) {
			l = mid;
		} else {
			r = mid - 1;
		}
	}

	return l;
}

func doable(tasks, workers []int, k, pills, strength int) bool {
	q := make([]int, 0, k);
	tIdx := 0;
	front := 0;

	for i := len(workers) - k; i < len(workers); i++ {
		W := workers[i];
		for tIdx < k && tasks[tIdx] <= W+strength {
			q = append(q, tasks[tIdx]);
			tIdx++;
		}

		if front == len(q) {
			return false;
		}

		if q[front] <= W {
			front++;
		} else {
			if pills == 0 {
				return false;
			}

			pills--;
			q = q[:len(q)-1]; // remove last (hardest task)
		}
	}

	return true;
}

func min(a, b int) int {
	if a < b {
		return a;
	}

	return b;
}