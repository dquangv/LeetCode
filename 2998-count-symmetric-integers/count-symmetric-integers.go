func countSymmetricIntegers(low int, high int) int {
    result := 0;

    for i := low; i <= high; i++ {
        if i < 100 && i % 10 == i / 10 || i > 999 && (i / 1000 + i / 100 % 10) == (i / 10 % 10 + i % 10) {
            result++;
        }
    }

    return result;
}