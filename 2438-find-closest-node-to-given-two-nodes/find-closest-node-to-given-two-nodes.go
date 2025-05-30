func closestMeetingNode(edges []int, node1 int, node2 int) int {
	dist1 := getDistances(edges, node1);
	dist2 := getDistances(edges, node2);
	n := len(edges);
	minDist := int(1e9);
	answer := -1;

	for i := 0; i < n; i++ {
		if dist1[i] != -1 && dist2[i] != -1 {
			maxDist := max(dist1[i], dist2[i]);
			if maxDist < minDist {
				minDist = maxDist;
				answer = i;
			}
		}
	}
    
	return answer;
}

func getDistances(edges []int, start int) []int {
	n := len(edges);
	dist := make([]int, n);

	for i := 0; i < n; i++ {
		dist[i] = -1;
	}

	visited := make([]bool, n);
	d := 0;
	for start != -1 && !visited[start] {
		dist[start] = d;
		visited[start] = true;
		start = edges[start];
		d++;
	}

	return dist;
}

func max(a, b int) int {
	if a > b {
		return a;
	}

	return b;
}
