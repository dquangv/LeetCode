func countGood(nums []int, k int) int64 {
    if len(nums) < 2 {
        return 0;
    }
    
    freq := make(map[int]int);
    n := len(nums);
    left := 0;
    var pairs, result int64 = 0, 0;

    for right := 0; right < n; right++ {
        val := nums[right];
        count := freq[val];
        pairs += int64(count);
        freq[val]++;

        for pairs >= int64(k) {
            result += int64(n - right);
            leftVal := nums[left];
            freq[leftVal]--;
            pairs -= int64(freq[leftVal]);
            left++;
        }
    }

    return result;
}