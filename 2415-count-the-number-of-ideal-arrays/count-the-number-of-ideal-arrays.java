class Solution {

    static final int MOD = 1_000_000_007;
    static final int MAX_NUM = 10010;
    static final int MAX_EXP = 15; // max số mũ trong phân tích nguyên tố
    static int[][] comb = new int[MAX_NUM + MAX_EXP][MAX_EXP + 1];
    static int[] smallestPrime = new int[MAX_NUM];
    static List<Integer>[] primeExponents = new List[MAX_NUM]; // mũ của thừa số nguyên tố

    public Solution() {
        // Đảm bảo chỉ init 1 lần
        if (comb[0][0] == 1) return;

        // Khởi tạo danh sách
        for (int i = 0; i < MAX_NUM; i++) {
            primeExponents[i] = new ArrayList<>();
        }

        // Sàng Eratosthenes để tìm thừa số nguyên tố nhỏ nhất của mỗi số
        for (int i = 2; i < MAX_NUM; i++) {
            if (smallestPrime[i] == 0) {
                for (int j = i; j < MAX_NUM; j += i) {
                    if (smallestPrime[j] == 0) {
                        smallestPrime[j] = i;
                    }
                }
            }
        }

        // Phân tích thừa số nguyên tố mỗi số và lưu mũ các thừa số
        for (int i = 2; i < MAX_NUM; i++) {
            int x = i;
            while (x > 1) {
                int p = smallestPrime[x], count = 0;
                while (x % p == 0) {
                    x /= p;
                    count++;
                }
                primeExponents[i].add(count);
            }
        }

        // Tiền xử lý tổ hợp: C(n, k)
        comb[0][0] = 1;
        for (int i = 1; i < MAX_NUM + MAX_EXP; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= Math.min(i, MAX_EXP); j++) {
                comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
            }
        }
    }

    public int idealArrays(int length, int maxValue) {
        long totalWays = 0;

        for (int num = 1; num <= maxValue; num++) {
            long ways = 1;

            // Với mỗi số num, xét các mũ thừa số nguyên tố của nó
            for (int exponent : primeExponents[num]) {
                // Số cách phân phối exponent vào length vị trí
                ways = (ways * comb[length + exponent - 1][exponent]) % MOD;
            }

            totalWays = (totalWays + ways) % MOD;
        }

        return (int) totalWays;
    }
}
