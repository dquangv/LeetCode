func minOperations(nums []int, k int) int {
    var arr [101]bool;
    result := 0;
    arr[k] = true;

    for _, num := range nums {
        if num < k {
            return -1;
        }

        if !arr[num] {
            result++;
            arr[num] = true;
        }
    }

    return result;
}