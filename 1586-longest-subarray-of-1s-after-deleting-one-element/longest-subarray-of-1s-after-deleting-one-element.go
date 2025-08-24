func longestSubarray(nums []int) int {
    left := 0
    zeroCount := 0
    result := 0

    for right := 0; right < len(nums); right++ {
        if nums[right] == 0 {
            zeroCount++
        }

        for zeroCount > 1 {
            if nums[left] == 0 {
                zeroCount--
            }
            left++
        }

        result = max(result, right-left)
    }

    return result
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}