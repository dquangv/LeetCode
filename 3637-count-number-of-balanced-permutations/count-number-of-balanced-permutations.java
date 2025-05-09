public class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private static final int[][] COMB = pascalTriangle();

    private int sum = 0, n;
    private int[] freq = new int[10];
    private Map<Long, Integer> dp = new HashMap<>();

    // Build Pascal triangle (Combination table)
    private static int[][] pascalTriangle() {
        int[][] comb = new int[41][41];
        for (int i = 0; i < 41; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j <= i / 2; j++) {
                long x = (long) comb[i - 1][j - 1] + comb[i - 1][j];
                if (x >= MOD) x -= MOD;
                comb[i][j] = comb[i][i - j] = (int) x;
            }
        }
        
        return comb;
    }

    // Recursive function with memoization
    private int f(int i, int even, int odd, int remain) {
        if (even == 0 && odd == 0 && remain == 0) return 1;
        if (i < 0 || even < 0 || odd < 0 || remain < 0) return 0;

        // Encode state to a unique key using bit shifting
        long key = (((long) i) << 42) | ((long) even << 32) | ((long) odd << 22) | remain;
        if (dp.containsKey(key)) return dp.get(key);

        long ans = 0;
        int maxJ = Math.min(freq[i], even);

        for (int j = 0; j <= maxJ; j++) {
            int oddRem = freq[i] - j;
            if (oddRem <= odd && remain >= i * j) {
                if (remain - i * j > (long)(even - j + odd - oddRem) * (i - 1))
                    continue; // Prune invalid branches

                long tmp = 1L * COMB[even][j] * COMB[odd][oddRem] % MOD;
                tmp = tmp * f(i - 1, even - j, odd - oddRem, remain - i * j) % MOD;
                ans = (ans + tmp) % MOD;
            }
        }

        dp.put(key, (int) ans);
        return (int) ans;
    }

    public int countBalancedPermutations(String num) {
        Arrays.fill(freq, 0);
        dp.clear();
        sum = 0;
        n = num.length();

        for (char c : num.toCharArray()) {
            int digit = c - '0';
            freq[digit]++;
            sum += digit;
        }

        // Total sum must be even to split into two equal halves
        if ((sum & 1) == 1) return 0;
        sum /= 2;

        int oddLen = n / 2;
        int evenLen = n - oddLen;

        return f(9, evenLen, oddLen, sum);
    }
}