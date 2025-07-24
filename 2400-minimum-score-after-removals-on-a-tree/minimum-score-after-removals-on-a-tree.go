func minimumScore(nums []int, edges [][]int) int {
	n := len(nums)

	// Build undirected tree adjacency list
	graph := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		graph[u] = append(graph[u], v)
		graph[v] = append(graph[v], u)
	}

	subtreeXor := make([]int, n)
	// descendants[i] = set of all nodes in subtree rooted at i
	descendants := make([]map[int]struct{}, n)
	for i := 0; i < n; i++ {
		descendants[i] = make(map[int]struct{})
	}

	var dfs func(node, parent int)
	dfs = func(node, parent int) {
		subtreeXor[node] = nums[node]
		descendants[node][node] = struct{}{}
		for _, nei := range graph[node] {
			if nei == parent {
				continue
			}
			dfs(nei, node)
			subtreeXor[node] ^= subtreeXor[nei]
			for d := range descendants[nei] {
				descendants[node][d] = struct{}{}
			}
		}
	}
	dfs(0, -1)

	totalXor := subtreeXor[0]
	minScore := math.MaxInt64

	// Try all unordered pairs (i, j) representing cutting the parent edge of i and parent edge of j
	for i := 1; i < n; i++ {
		for j := i + 1; j < n; j++ {
			xorI := subtreeXor[i]
			xorJ := subtreeXor[j]
			var v1, v2, v3 int

			_, jInI := descendants[i][j]
			_, iInJ := descendants[j][i]

			switch {
			// Case 1: j is inside subtree of i
			case jInI:
				v1 = xorJ
				v2 = xorI ^ xorJ
				v3 = totalXor ^ xorI
			// Case 2: i is inside subtree of j
			case iInJ:
				v1 = xorI
				v2 = xorJ ^ xorI
				v3 = totalXor ^ xorJ
			// Case 3: independent subtrees
			default:
				v1 = xorI
				v2 = xorJ
				v3 = totalXor ^ xorI ^ xorJ
			}

			// Compute score
			maxv := v1
			if v2 > maxv {
				maxv = v2
			}
			if v3 > maxv {
				maxv = v3
			}
			minv := v1
			if v2 < minv {
				minv = v2
			}
			if v3 < minv {
				minv = v3
			}
			score := maxv - minv
			if score < minScore {
				minScore = score
			}
		}
	}

	return minScore
}