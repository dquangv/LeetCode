func findNumbers(nums []int) int {
    result := 0;

    for _, num := range nums {
        if num == 100000 || (num < 10000 && num > 999) || (num < 100 && num > 9) {
            result++;
        }
    }

    return result;
}