class Solution {
    static final int MOD = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        long[] freq = new long[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        long[][] trans = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int steps = nums.get(i);
            for (int j = 1; j <= steps; j++) {
                int to = (i + j) % 26;
                trans[i][to] = (trans[i][to] + 1) % MOD;
            }
        }

        long[][] transT = matrixPower(trans, t);

        long[] resultFreq = new long[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                resultFreq[j] = (resultFreq[j] + freq[i] * transT[i][j]) % MOD;
            }
        }

        long total = 0;
        for (int i = 0; i < 26; i++) {
            total = (total + resultFreq[i]) % MOD;
        }

        return (int) total;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < 26; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < 26; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }

    private long[][] matrixPower(long[][] base, int power) {
        long[][] result = new long[26][26];

        for (int i = 0; i < 26; i++) {
            result[i][i] = 1;
        }

        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            power >>= 1;
        }
        
        return result;
    }
}
