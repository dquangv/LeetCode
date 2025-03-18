func longestNiceSubarray(nums []int) int {
    left := 0;
    usedBits := 0;
    maxLen := 0;

    for right := 0; right < len(nums); right++ {

        for (usedBits & nums[right]) != 0 {
            usedBits ^= nums[left];
            left++;
        }

        usedBits |= nums[right];

        windowLen := right - left + 1;

        if windowLen > maxLen {
            maxLen = windowLen;
        }
    }

    return maxLen;
}