func largestPathValue(colors string, edges [][]int) int {
	n := len(colors);
	adj := make([][]int, n);
	indegree := make([]int, n);

	// Build graph and indegree count
	for _, e := range edges {
		u, v := e[0], e[1];
		adj[u] = append(adj[u], v);
		indegree[v]++;
	}

	// Initialize dp table: dp[i][c] is max count of color c to node i
	dp := make([][]int, n);
	for i := range dp {
		dp[i] = make([]int, 26);
	}

	queue := []int{};
	for i := 0; i < n; i++ {
		if indegree[i] == 0 {
			queue = append(queue, i);
		}
	}

	seen := 0;
	ans := 0;

	for len(queue) > 0 {
		u := queue[0];
		queue = queue[1:];
		seen++;

		c := int(colors[u] - 'a');
		dp[u][c]++;
		if dp[u][c] > ans {
			ans = dp[u][c];
		}

		for _, v := range adj[u] {
			for k := 0; k < 26; k++ {
				if dp[u][k] > dp[v][k] {
					dp[v][k] = dp[u][k];
				}
			}
			indegree[v]--;
			if indegree[v] == 0 {
				queue = append(queue, v);
			}
		}
	}

	if seen != n {
		return -1; // Có chu trình
	}
    
	return ans;
}
