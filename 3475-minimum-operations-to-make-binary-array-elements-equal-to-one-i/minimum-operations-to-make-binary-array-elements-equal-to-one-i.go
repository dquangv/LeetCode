func minOperations(nums []int) int {
    result := 0;

    for i := 0; i < len(nums) - 2; i++ {
        if nums[i] != 0 {
            continue;
        }

        nums[i] = 1;
        nums[i+1] ^= 1;
        nums[i+2] ^= 1;

        result += 1;
    }

    if nums[len(nums) - 2] + nums[len(nums) - 1] != 2 {
        return -1;
    }

    return result;
}