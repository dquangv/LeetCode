func maximumTripletValue(nums []int) int64 {
    n := len(nums);
    L := int64(nums[0]);
    maxD := L - int64(nums[1]);
    result := max(0, maxD * int64(nums[2]));

    for i := 3; i < n; i++ {
        L = max(L, int64(nums[i - 2]));
        maxD = max(maxD, L - int64(nums[i - 1]));
        result = max(result, maxD * int64(nums[i]));
    }

    return result;
}

func max(a, b int64) int64 {
    if a < b {
        return b;
    }

    return a;
}