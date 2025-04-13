class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long part1 = powMod(5, evenCount);
        long part2 = powMod(4, oddCount);

        return (int) ((part1 * part2) % MOD);
    }

    private long powMod(long a, long b) {
        long result = 1;

        a %= MOD;

        while (b > 0) {
            if ((b & 1) == 1) result = (result * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }

        return result;
    }
}