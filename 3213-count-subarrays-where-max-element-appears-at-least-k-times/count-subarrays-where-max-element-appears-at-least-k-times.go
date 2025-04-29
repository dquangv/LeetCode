func countSubarrays(nums []int, k int) int64 {
    n := len(nums);
    maxVal := 0;
    for _, num := range nums {
        maxVal = max(maxVal, num);
    }

    var result int64;
    count, left := 0, 0;

    for right := 0; right < n ; right++ {
        if nums[right] == maxVal {
            count++;
        }

        for count >= k {
            if nums[left] == maxVal {
                count--;
            }

            left++;
        }

        result += int64(left);
    }

    return result;
}

func max(a, b int) int {
    if a > b {
        return a;
    }

    return b;
}