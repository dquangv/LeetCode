class Solution {
    public int lenOfVDiagonal(int[][] g) {
        int n = g.length, m = g[0].length;
        int[][] D = { { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };

        int[][][] dp0 = new int[4][n][m];
        int[][][] dp2 = new int[4][n][m];
        int res = 0;

        // Fill DP arrays
        for (int d = 0; d < 4; d++) {
            int dr = D[d][0], dc = D[d][1];
            int[] rows, cols;

            if (dr >= 0) {
                rows = new int[n];
                for (int i = 0; i < n; i++)
                    rows[i] = n - 1 - i;
            } else {
                rows = new int[n];
                for (int i = 0; i < n; i++)
                    rows[i] = i;
            }

            if (dc >= 0) {
                cols = new int[m];
                for (int j = 0; j < m; j++)
                    cols[j] = m - 1 - j;
            } else {
                cols = new int[m];
                for (int j = 0; j < m; j++)
                    cols[j] = j;
            }

            for (int i : rows)
                for (int j : cols) {
                    int ni = i + dr, nj = j + dc;
                    if (g[i][j] == 0)
                        dp0[d][i][j] = 1 + (inb(ni, nj, n, m) ? dp2[d][ni][nj] : 0);

                    if (g[i][j] == 2)
                        dp2[d][i][j] = 1 + (inb(ni, nj, n, m) ? dp0[d][ni][nj] : 0);
                }
        }

        // Try every start with "1"
        for (int d = 0; d < 4; d++) {
            int dr = D[d][0], dc = D[d][1];
            int t = (d + 1) % 4;

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    if (g[i][j] != 1)
                        continue;
                    int r = i, c = j, need = 1, L = 0;

                    while (inb(r, c, n, m) && g[r][c] == need) {
                        L++;
                        res = Math.max(res, L);

                        // Try turning
                        int tr = r + D[t][0], tc = c + D[t][1];
                        if (inb(tr, tc, n, m))
                            if ((L & 1) == 1)
                                res = Math.max(res, L + dp2[t][tr][tc]);
                            else
                                res = Math.max(res, L + dp0[t][tr][tc]);

                        r += dr;
                        c += dc;
                        if (need == 1)
                            need = 2;
                        else if (need == 2)
                            need = 0;
                        else
                            need = 2;
                    }
                }
        }

        return res;
    }

    private boolean inb(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
