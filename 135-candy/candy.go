func candy(ratings []int) int {
    n := len(ratings);
    candies := make([]int, n);

    for i := 0; i < n; i++ {
        candies[i] = 1;
    }

    for i := 1; i < n; i++ {
        if ratings[i] > ratings[i - 1] {
            candies[i] = candies[i - 1] + 1;
        }
    }

    for i := n - 2; i >=0; i-- {
        if ratings[i] > ratings[i + 1] {
            candies[i] = max(candies[i], candies[i + 1] + 1);
        }
    }

    result := 0;
    for _, candy := range candies {
        result += candy;
    }

    return result;
}