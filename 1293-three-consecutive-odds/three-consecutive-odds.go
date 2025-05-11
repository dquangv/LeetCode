func threeConsecutiveOdds(arr []int) bool {
    odd := 0;
    for _, num := range arr {
        if num % 2 != 0 {
            odd++;
            if odd == 3 {
                return true;
            }
        } else {
            odd = 0;
        }
    }

    return false;
}