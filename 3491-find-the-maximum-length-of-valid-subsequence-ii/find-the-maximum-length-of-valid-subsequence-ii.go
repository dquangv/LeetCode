func maximumLength(nums []int, k int) int {
    n := len(nums)
    if k == 1 {
        return n
    }

    res := 2
    modArr := make([]int, n)
    for i := 0; i < n; i++ {
        modArr[i] = nums[i] % k
    }

    for j := 0; j < k; j++ {
        dp := make([]int, k)
        for i := 0; i < n; i++ {
            mod := modArr[i]
            pos := (j - mod + k) % k
            dp[mod] = dp[pos] + 1
            if dp[mod] > res {
                res = dp[mod]
            }
        }
    }

    return res
}
