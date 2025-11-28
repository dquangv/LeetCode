func maxKDivisibleComponents(n int, edges [][]int, values []int, k int) int {
	adjList := make([][]int, n)
	for _, edge := range edges {
		node1 := edge[0]
		node2 := edge[1]
		adjList[node1] = append(adjList[node1], node2)
		adjList[node2] = append(adjList[node2], node1)
	}

	var components int
	dfs(0, -1, adjList, values, k, &components)

	return components
}

func dfs(curNode int, parentNode int, adjList [][]int, values []int, k int, components *int) int {
	sum := 0

	for _, edge := range adjList[curNode] {
		if edge == parentNode {
			continue
		}
		sum += dfs(edge, curNode, adjList, values, k, components)
		sum %= k
	}

	sum += values[curNode]
	sum %= k

	if sum == 0 {
		(*components)++
	}

	return sum
}