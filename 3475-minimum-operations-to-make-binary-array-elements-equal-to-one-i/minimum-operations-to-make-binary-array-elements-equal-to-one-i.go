func minOperations(nums []int) int {
    result := 0;

    for i := 0; i < len(nums) - 2; i++ {
        if nums[i] == 1 {
            continue;
        }

        nums[i] ^= 1;
        nums[i+1] ^= 1;
        nums[i+2] ^= 1;

        result++;
    }

    if nums[len(nums) - 2] == 0 || nums[len(nums) - 1] == 0 {
        return -1;
    }

    return result;
}