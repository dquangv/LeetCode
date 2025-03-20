func find(parent []int, node int) int {
	if parent[node] == node {
		return node;
	}

	parent[node] = find(parent, parent[node]);

	return parent[node];
}

func union(parent, rank, minPathAND []int, u, v int) {
	if rank[u] > rank[v] {
		parent[v] = u;
		minPathAND[u] &= minPathAND[v];
	} else if rank[u] < rank[v] {
		parent[u] = v;
		minPathAND[v] &= minPathAND[u];
	} else {
		parent[v] = u;
		minPathAND[u] &= minPathAND[v];
		rank[u]++;
	}
}

func minimumCost(n int, edges [][]int, query [][]int) []int {
    parent := make([]int, n);
	rank := make([]int, n);
	minPathAND := make([]int, n);

	for i := 0; i < n; i++ {
		parent[i] = i;
		rank[i] = 1;
		minPathAND[i] = -1;
	}

	for _, edge := range edges {
		u := edge[0];
		v := edge[1];
		weight := edge[2];

		rootU := find(parent, u);
		rootV := find(parent, v);

		if rootU != rootV {
			union(parent, rank, minPathAND, rootU, rootV);
			rootU = find(parent, u);
		}

		minPathAND[rootU] &= weight;
	}

	result := make([]int, len(query));

	for i, q := range query {
		u := q[0];
		v := q[1];

		rootU := find(parent, u);
		rootV := find(parent, v);

		if u == v {
			result[i] = 0;
		} else if rootU != rootV {
			result[i] = -1;
		} else {
			result[i] = minPathAND[rootU];
		}
	}

	return result;
}