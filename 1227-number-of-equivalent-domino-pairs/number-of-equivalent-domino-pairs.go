func numEquivDominoPairs(dominoes [][]int) int {
    count := [100]int{};
    result := 0;

    for _, domino := range dominoes {
        a, b := domino[0], domino[1];
        key := a*10 + b;

        if a > b {
            key = b*10 + a;
        }

        result += count[key];
        count[key]++;
    }

    return result;
}