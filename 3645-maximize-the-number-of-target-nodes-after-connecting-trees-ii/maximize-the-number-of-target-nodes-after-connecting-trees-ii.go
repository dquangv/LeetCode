type Solution struct {
	evenA, oddA int;
	evenB, oddB int;
}

// Tạo danh sách kề từ cạnh
func buildList(edges [][]int) [][]int {
	n := len(edges) + 1;
	adj := make([][]int, n);
	for _, e := range edges {
		u, v := e[0], e[1];
		adj[u] = append(adj[u], v);
		adj[v] = append(adj[v], u);
	}
	return adj;
}

// DFS để tô màu và đếm chẵn/lẻ
func (s *Solution) dfsColor(adj [][]int, u, parent int, color []int, isA bool) {
	if color[u] == 0 {
		if isA {
			s.evenA++;
		} else {
			s.evenB++;
		}
	} else {
		if isA {
			s.oddA++;
		} else {
			s.oddB++;
		}
	}
	for _, v := range adj[u] {
		if v != parent {
			color[v] = color[u] ^ 1;
			s.dfsColor(adj, v, u, color, isA);
		}
	}
}

func maxTargetNodes(edges1 [][]int, edges2 [][]int) []int {
	s := &Solution{};
	adjA := buildList(edges1);
	adjB := buildList(edges2);

	n := len(adjA);
	m := len(adjB);

	colorA := make([]int, n);
	colorB := make([]int, m);
	for i := 0; i < n; i++ {
		colorA[i] = -1;
	}
	for i := 0; i < m; i++ {
		colorB[i] = -1;
	}

	// DFS từ gốc để tô màu và đếm
	colorA[0] = 0;
	s.dfsColor(adjA, 0, -1, colorA, true);
	colorB[0] = 0;
	s.dfsColor(adjB, 0, -1, colorB, false);

	maxiB := max(s.evenB, s.oddB);

	res := make([]int, n);
	for i := 0; i < n; i++ {
		if colorA[i] == 0 {
			res[i] = s.evenA + maxiB;
		} else {
			res[i] = s.oddA + maxiB;
		}
	}
	return res;
}

func max(a, b int) int {
	if a > b {
		return a;
	}
	return b;
}