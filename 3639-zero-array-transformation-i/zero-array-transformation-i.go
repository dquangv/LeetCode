func isZeroArray(nums []int, queries [][]int) bool {
    n := len(nums);
    count := make([]int, n + 1);

    for _, q := range queries {
        count[q[0]]++;
        count[q[1] + 1]--;
    }

    sum := 0;
    for i := 0; i < n; i++ {
        sum += count[i];
        count[i] = sum;
        if count[i] < nums[i] {
            return false;
        }
    }

    return true;
}