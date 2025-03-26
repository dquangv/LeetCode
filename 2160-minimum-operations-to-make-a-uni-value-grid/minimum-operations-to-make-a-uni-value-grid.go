func minOperations(grid [][]int, x int) int {
    m, n := len(grid), len(grid[0]);
	size := m * n;
	arr := make([]int, size);
	remainder := grid[0][0] % x;

    for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j]%x != remainder {
				return -1;
			}

			arr[i*n+j] = grid[i][j];
		}
	}

    sort.Ints(arr);
	median := arr[size/2];
    operations := 0;

	for _, num := range arr {
		operations += abs(num - median) / x;
	}

	return operations;
}

func abs(a int) int {
	if a < 0 {
		return -a;
	}

	return a;
}