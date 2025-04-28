func countSubarrays(nums []int, k int64) int64 {
    var result int64 = 0;
    var sum int64 = 0;
    left := 0;

    for right := 0; right < len(nums); right ++ {
        sum += int64(nums[right]);

        for left <= right && sum * int64(right - left + 1) >= k {
            sum -= int64(nums[left]);
            left++;
        }

        result += int64(right - left + 1);
    }

    return result;
}