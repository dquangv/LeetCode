func countSymmetricIntegers(low int, high int) int {
    result := 0;

    for i := low; i <= high; i++ {
        s := strconv.Itoa(i);
        n := len(s);

        if n % 2 != 0 {
            continue;
        }

        half := n / 2;
        sum1, sum2 := 0, 0;

        for j := 0; j < half; j++ {
            sum1 += int(s[j] - '0');
            sum2 += int(s[j+half] - '0');
        }

        if sum1 == sum2 {
            result++;
        }
    }

    return result;
}