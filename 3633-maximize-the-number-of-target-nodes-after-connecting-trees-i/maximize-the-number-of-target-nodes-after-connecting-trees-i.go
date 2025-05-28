func maxTargetNodes(edges1 [][]int, edges2 [][]int, k int) []int {
	n := len(edges1) + 1;
	m := len(edges2) + 1;

	// Tạo đồ thị kề
	buildAdj := func(edges [][]int, size int) [][]int {
		adj := make([][]int, size);
		for _, e := range edges {
			u, v := e[0], e[1];
			adj[u] = append(adj[u], v);
			adj[v] = append(adj[v], u);
		}

		return adj;
	}

	// DFS đếm số node trong phạm vi maxLen
	var dfs func(i, parent, maxLen int, adj [][]int) int;
	dfs = func(i, parent, maxLen int, adj [][]int) int {
		if maxLen < 0 {
			return 0;
		}

		count := 1;
		for _, j := range adj[i] {
			if j == parent {
				continue;
			}

			count += dfs(j, i, maxLen-1, adj);
		}

		return count;
	}

	adj1 := buildAdj(edges1, n);
	adj2 := buildAdj(edges2, m);

	cnt2 := 0;
	for i := 0; i < m; i++ {
		cnt2 = max(cnt2, dfs(i, -1, k-1, adj2));
	}

	ans := make([]int, n);
	for i := 0; i < n; i++ {
		ans[i] = dfs(i, -1, k, adj1) + cnt2;
	}

	return ans;
}

func max(a, b int) int {
	if a > b {
		return a;
	}

	return b;
}
