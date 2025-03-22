func countCompleteComponents(n int, edges [][]int) int {
    adj := make([][]int, n);

	for _, edge := range edges {
		nodeA, nodeB := edge[0], edge[1];
		adj[nodeA] = append(adj[nodeA], nodeB);
		adj[nodeB] = append(adj[nodeB], nodeA);
	}

	visited := make([]bool, n);
	count := 0;

	for i := 0; i < n; i++ {
		if !visited[i] {
			component := []int{};
			edgeCount := dfs(i, visited, adj, &component);
			nodeCount := len(component);

			if nodeCount*(nodeCount-1) == edgeCount {
				count++;
			}
		}
	}

	return count;
}

func dfs(node int, visited []bool, adj [][]int, component *[]int) int {
	visited[node] = true;
	*component = append(*component, node);
	edgeCount := 0;

	for _, neighbor := range adj[node] {
		if !visited[neighbor] {
			edgeCount += dfs(neighbor, visited, adj, component);
		}

		edgeCount++;
	}

	return edgeCount;
}