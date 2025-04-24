func countCompleteSubarrays(nums []int) int {
    distinctSet := make(map[int]struct{});
    for _, val := range nums {
        distinctSet[val] = struct{}{};
    }

    totalUnique := len(distinctSet);
    left := 0;
    count := 0;
    windowFreq := make(map[int]int);

    for right := 0; right < len(nums); right++ {
        windowFreq[nums[right]]++;

        for len(windowFreq) == totalUnique {
            count += len(nums) - right;
            windowFreq[nums[left]]--;

            if windowFreq[nums[left]] == 0 {
                delete(windowFreq, nums[left]);
            }

            left++;
        }
    }

    return count;
}