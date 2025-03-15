func minCapability(nums []int, k int) int {
    f := func(maxCapability int) bool {
        chosenCount := 0;

        for i := 0; i < len(nums); i++ {
            if nums[i] <= maxCapability {
                chosenCount += 1;
                i++;
            }
        }
        return chosenCount >= k;
    }
    
    return findCapabilityLimit(1e9, f);
}

func findCapabilityLimit(maxSearchValue int, isFeasible func(int) bool) int {
    left := 0;
    right := maxSearchValue;

    for left < right {
        mid := left + (right - left) / 2;
        if !isFeasible(mid) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}