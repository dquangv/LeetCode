func maxAdjacentDistance(nums []int) int {
    result := abs(nums[0] - nums[len(nums) - 1]);
    for i := 0; i < len(nums) - 1; i++ {
        result = max(result, abs(nums[i] - nums[i + 1]));
    }

    return result;
}

func abs(a int) int {
    if a < 0 {
        return -a;
    }
    return a;
}