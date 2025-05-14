class Solution {
    static final int MOD = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        // Bước 1: Tạo vector tần suất ký tự ban đầu
        long[] freq = new long[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;

        // Bước 2: Xây dựng ma trận chuyển tiếp 26x26
        // trans[i][j] là số lần ký tự i biến thành ký tự j sau 1 lần transform
        long[][] trans = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int steps = nums.get(i);
            for (int j = 1; j <= steps; j++) {
                int to = (i + j) % 26;
                trans[i][to] = (trans[i][to] + 1) % MOD;
            }
        }

        // Bước 3: Tính lũy thừa ma trận trans^t
        long[][] transT = matrixPower(trans, t);

        // Bước 4: Nhân vector ban đầu với ma trận kết quả
        long[] resultFreq = new long[26];
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < 26; j++)
                resultFreq[j] = (resultFreq[j] + freq[i] * transT[i][j]) % MOD;

        // Bước 5: Tính tổng tất cả các ký tự sau t lần transform
        long total = 0;
        for (int i = 0; i < 26; i++)
            total = (total + resultFreq[i]) % MOD;

        return (int) total;
    }

    // Hàm nhân 2 ma trận 26x26
    private long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < 26; k++) {
                if (A[i][k] == 0)
                    continue;

                for (int j = 0; j < 26; j++)
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
            }
        }

        return C;
    }

    // Hàm tính lũy thừa ma trận A^power bằng chia để trị
    private long[][] matrixPower(long[][] base, int power) {
        long[][] result = new long[26][26];
        // Ma trận đơn vị (identity)
        for (int i = 0; i < 26; i++)
            result[i][i] = 1;

        while (power > 0) {
            if ((power & 1) == 1)
                result = multiply(result, base);

            base = multiply(base, base);
            power >>= 1;
        }

        return result;
    }
}
