func largestDivisibleSubset(nums []int) []int {
    n := len(nums);

    if n == 0 {
        return []int{};
    }

    sort.Ints(nums);

    dp := make([]int, n);
    prev := make([]int, n);
    maxLen := 1;
    maxIndex := 0;

    for i := 0; i < n; i++ {
        dp[i] = 1;
        prev[i] = -1;

        for j := 0; j < i; j++ {
            if nums[i]%nums[j] == 0 && dp[j]+1 > dp[i] {
                dp[i] = dp[j] + 1;
                prev[i] = j;
            }
        }

        if dp[i] > maxLen {
            maxLen = dp[i];
            maxIndex = i;
        }
    }

    result := []int{};
    for maxIndex != -1 {
        result = append(result, nums[maxIndex]);
        maxIndex = prev[maxIndex];
    }

    reverse(result);

    return result;
}

func reverse(nums []int) {
    for i, j := 0, len(nums)-1; i < j; i, j = i+1, j-1 {
        nums[i], nums[j] = nums[j], nums[i];
    }
}