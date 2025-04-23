func countLargestGroup(n int) int {
    digitSumCount := make([]int, 37);
    digitSum := make([]int, n + 1);
    maxGroupSize, result := 0, 0;

    digitSumCount[0] = -1;
    
    for i := 0; i <= n; i++ {
        digitSum[i] = i % 10 + digitSum[i / 10];
        digitSumCount[digitSum[i]]++;
    }

    for _, group := range digitSumCount {
        if maxGroupSize < group {
            maxGroupSize = group;
            result = 0;
        }

        if maxGroupSize == group {
            result++;
        }
    }

    return result;
}