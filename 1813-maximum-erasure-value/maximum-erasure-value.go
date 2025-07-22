func maximumUniqueSubarray(nums []int) int {
    seen := make(map[int]bool)
    currentSum, result := 0, 0
    start := 0

    for end := 0; end < len(nums); end++ {
        for seen[nums[end]] {
            seen[nums[start]] = false
            currentSum -= nums[start]
            start++
        }

        currentSum += nums[end]
        seen[nums[end]] = true
        if currentSum > result {
            result = currentSum
        }
    }

    return result
}
