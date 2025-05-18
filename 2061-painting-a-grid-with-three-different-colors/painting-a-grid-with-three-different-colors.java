class Solution {
    static final int MOD = (int)1e9 + 7;
    static int[][] I;
    int[][] M;
    List<String> Type;
    int i0 = 0;
    int n, m;
    int[] map = new int[27]; // encoding pattern -> typeId

    // Identity matrix of size sz
    int[][] identity(int sz) {
        int[][] I = new int[sz][sz];
        for (int i = 0; i < sz; i++)
            I[i][i] = 1;
        return I;
    }

    // Matrix multiplication
    int[][] multiply(int[][] A, int[][] B) {
        int n = A.length, p = A[0].length, q = B.length, m = B[0].length;
        if (p != q) return new int[0][0];
        int[][] C = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < p; k++) {
                for (int j = 0; j < m; j++) {
                    C[i][j] = (int)((C[i][j] + 1L * A[i][k] * B[k][j]) % MOD);
                }
            }
        }
        return C;
    }

    // Modular matrix exponentiation
    int[][] modPow(int[][] M, int n, int sz) {
        if (n == 0) return I;
        int[][] ans = M;
        for (int i = 31 - Integer.numberOfLeadingZeros(n) - 1; i >= 0; i--) {
            ans = multiply(ans, ans);
            if (((n >> i) & 1) != 0)
                ans = multiply(ans, M);
        }
        return ans;
    }

    // Modular integer exponentiation
    int modPow(int base, int n) {
        long ans = 1, b = base % MOD;
        for (int i = 31 - Integer.numberOfLeadingZeros(n); i >= 0; i--) {
            ans = ans * ans % MOD;
            if (((n >> i) & 1) != 0)
                ans = ans * b % MOD;
        }
        return (int)ans;
    }

    void buildType(int i, StringBuilder pattern) {
        if (i == 0) {
            int key = 0;
            for (int j = 2; j < m; j++)
                key = key * 3 + (pattern.charAt(j) - '0');
            map[key] = i0;
            Type.add(pattern.toString());
            i0++;
            return;
        }
        for (char x = '0'; x <= '2'; x++) {
            if (x == pattern.charAt(pattern.length() - 1)) continue;
            pattern.append(x);
            buildType(i - 1, pattern);
            pattern.deleteCharAt(pattern.length() - 1);
        }
    }

    void findTypeId(String col, int colIndex) {
        char x = col.charAt(0), y = col.charAt(1), z = 0;
        for (char c : new char[] {'a', 'b', 'c'}) {
            if (c != x && c != y) z = c;
        }
        int[] f = new int[128];
        f[x] = 0; f[y] = 1; f[z] = 2;
        int key = 0;
        for (int j = 2; j < m; j++)
            key = key * 3 + f[col.charAt(j)];
        if (map[key] != -1)
            M[map[key]][colIndex]++;
    }

    void buildMatrix(int i, StringBuilder curr, String col0, int colIndex) {
        if (i < 0) {
            findTypeId(curr.toString(), colIndex);
            return;
        }
        for (char x = 'a'; x <= 'c'; x++) {
            if (x == (char)('a' + col0.charAt(i) - '0')) continue;
            if (curr.length() > 0 && x == curr.charAt(curr.length() - 1)) continue;
            curr.append(x);
            buildMatrix(i - 1, curr, col0, colIndex);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public int colorTheGrid(int m, int n) {
        this.m = m;
        this.n = n;
        if (m == 1) return (int)(3L * modPow(2, n - 1) % MOD);
        if (m == 2) return (int)(6L * modPow(3, n - 1) % MOD);

        int sz = 1 << (m - 2);
        I = identity(sz);
        Arrays.fill(map, -1);
        Type = new ArrayList<>();

        StringBuilder pattern = new StringBuilder("01");
        buildType(m - 2, pattern);

        M = new int[sz][sz];
        for (int i = 0; i < sz; i++) {
            String s = Type.get(i);
            buildMatrix(m - 1, new StringBuilder(), s, i);
        }

        int[][] A = modPow(M, n - 1, sz);
        long ans = 0;
        for (int i = 0; i < sz; i++) {
            long rowSum = 0;
            for (int v : A[i]) rowSum = (rowSum + v) % MOD;
            ans = (ans + 6L * rowSum % MOD) % MOD;
        }
        return (int)ans;
    }
}