func maxSum(nums []int) int {
    seen := make(map[int]bool)
    sum := 0
    hasNonNegative := false

    for _, num := range nums {
        if num >= 0 {
            hasNonNegative = true
            if !seen[num] {
                seen[num] = true
                sum += num
            }
        }
    }

    if hasNonNegative {
        return sum
    }

    // If all numbers are negative, return the maximum one
    maxNegative := nums[0]
    for _, num := range nums {
        if num > maxNegative {
            maxNegative = num
        }
    }

    return maxNegative
}