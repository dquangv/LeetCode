func findLHS(nums []int) int {
    freq := make(map[int]int)

    for _, num := range nums {
        freq[num]++
    }

    maxLen := 0
    for num, count := range freq {
        if nextCount, ok := freq[num + 1]; ok {
            total := count + nextCount
            if total > maxLen {
                maxLen = total
            }
        }
    }

    return maxLen
}