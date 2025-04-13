const MOD = int64(1e9 + 7);

func countGoodNumbers(n int64) int {
    evenCount := (n + 1) / 2;
    oddCount := n / 2;

    // 5 = {0, 2, 4, 6, 8}
    part1 := powMod(5, evenCount, MOD);
    // 4 = {2, 3, 5, 7}
    part2 := powMod(4, oddCount, MOD);

    return int((part1 * part2) % MOD);
}

func powMod(a, b, mod int64) int64 {
    result := int64(1);

    a %= mod;

    for b > 0 {
        if b % 2 == 1 {
            result = (result * a) % mod;
        }

        a = (a * a) % mod;
        b /= 2;
    }

    return result;
}