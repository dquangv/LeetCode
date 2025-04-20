func numRabbits(answers []int) int {
    maxArr := make(map[int]int);
	result := 0;

    for _, ans := range answers {
		maxArr[ans]++;

        if maxArr[ans] == 1 {
            result += ans + 1;
        }

        if maxArr[ans] == ans + 1 {
            maxArr[ans] = 0;
        }
	}

    return result;
}