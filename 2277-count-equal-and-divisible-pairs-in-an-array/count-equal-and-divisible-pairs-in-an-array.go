func countPairs(nums []int, k int) int {
    return recursion(nums, k, 0);
}

func recursion(nums []int, k, i int) int {
    if i > len(nums) - 2 {
        return 0;
    } 

    count := 0;
    for j := i + 1; j < len(nums) ; j ++ {
        if nums[i] == nums[j] && (i * j) % k == 0 {
            count++;
        }
    }

    return count + recursion(nums, k, i + 1);
}