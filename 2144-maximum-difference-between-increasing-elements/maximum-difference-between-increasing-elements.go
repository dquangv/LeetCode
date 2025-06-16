func maximumDifference(nums []int) int {
    min, dif := nums[0], 0;
    for i := 1; i < len(nums); i++ {
        if min > nums[i] {
            min = nums[i];
        } else {
            dif = max(dif, nums[i] - min);
        }
    }

    if dif > 0 {
        return dif;
    }

    return -1;
}