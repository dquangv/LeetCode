func countFairPairs(nums []int, lower int, upper int) int64 {
    sort.Ints(nums);

    return count(nums, upper) - count(nums, lower - 1);
}

func count(nums []int, x int) int64 {
    left, right := 0, len(nums) - 1;
    var result int64 = 0;

    for left < right {
        if nums[left] + nums[right] > x {
            right--;
        } else {
            result += int64(right - left);
            left++;
        }
    }

    return result;
}