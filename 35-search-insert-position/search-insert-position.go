func searchInsert(nums []int, target int) int {
    if nums[0] > target {
        return 0;
    }

    for i:= 0; i < len(nums); i++ {
        if nums[i] >= target {
            return i;
        }
    }

    return len(nums);
}