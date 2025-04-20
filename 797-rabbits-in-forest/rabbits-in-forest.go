func numRabbits(answers []int) int {
    countMap := make(map[int]int);
	result := 0;

    for _, ans := range answers {
		countMap[ans]++;
	}

    for ans, count := range countMap {
		groupSize := ans + 1;
		numGroups := (count + groupSize - 1) / groupSize;
		result += numGroups * groupSize;
	}

    return result;
}