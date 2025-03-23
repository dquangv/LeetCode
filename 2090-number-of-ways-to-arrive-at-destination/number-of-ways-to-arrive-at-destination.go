const MOD = int(1e9 + 7);

type NodeTime struct {
	node int
	time int64
}

type PriorityQueue []NodeTime;

func (pq PriorityQueue) Len() int { return len(pq); }

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].time < pq[j].time;
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i];
}

func (pq *PriorityQueue) Push(x interface{}) {
	*pq = append(*pq, x.(NodeTime));
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq;
	n := len(old);
	item := old[n-1];
	*pq = old[:n-1];

	return item;
}

func countPaths(n int, roads [][]int) int {
    adj := make([][]NodeTime, n);
    
	for _, road := range roads {
		u, v, time := road[0], road[1], road[2];
		adj[u] = append(adj[u], NodeTime{v, int64(time)});
		adj[v] = append(adj[v], NodeTime{u, int64(time)});
	}

	minDist := make([]int64, n);

	for i := range minDist {
		minDist[i] = 1e18;
	}

	pathCount := make([]int, n);

	minDist[0] = 0;
	pathCount[0] = 1;

	pq := &PriorityQueue{};
	heap.Init(pq);
	heap.Push(pq, NodeTime{0, 0});

	for pq.Len() > 0 {
		current := heap.Pop(pq).(NodeTime);
		currentNode := current.node;
		currentTime := current.time;

		if currentTime > minDist[currentNode] {
			continue;
		}

		for _, neighbor := range adj[currentNode] {
			nextNode := neighbor.node;
			edgeTime := neighbor.time;

			if minDist[nextNode] > currentTime+edgeTime {
				minDist[nextNode] = currentTime + edgeTime;
				heap.Push(pq, NodeTime{nextNode, minDist[nextNode]});
				pathCount[nextNode] = pathCount[currentNode];
			} else if minDist[nextNode] == currentTime+edgeTime {
				pathCount[nextNode] = (pathCount[nextNode] + pathCount[currentNode]) % MOD;
			}
		}
	}

	return pathCount[n-1];
}