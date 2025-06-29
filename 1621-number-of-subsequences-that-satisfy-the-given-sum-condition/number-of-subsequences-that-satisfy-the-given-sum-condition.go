func numSubseq(nums []int, target int) int {
    const mod = 1_000_000_007
    sort.Ints(nums)

    n := len(nums)
    power := make([]int, n)
    power[0] = 1
    for i := 1; i < n; i++ {
        power[i] = (power[i-1] * 2) % mod // Tiền xử lý 2^i % mod
    }

    answer := 0
    left, right := 0, n-1

    for left <= right {
        // Nếu nums[left] + nums[right] <= target thì
        // tất cả subsequence từ left đến right là hợp lệ
        if nums[left]+nums[right] <= target {
            answer = (answer + power[right-left]) % mod
            left++
        } else {
            right-- // giảm max cho đến khi thỏa điều kiện
        }
    }

    return answer
}
