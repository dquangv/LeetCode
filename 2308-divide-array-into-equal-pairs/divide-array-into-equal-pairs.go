func divideArray(nums []int) bool {
    pairs := make(map[int]int);

    for _, num := range nums {
        pairs[num]++;
    }

    for _, pair := range pairs {
        
        if pair % 2 == 1 {
            return false;
        }
    }

    return true;
}