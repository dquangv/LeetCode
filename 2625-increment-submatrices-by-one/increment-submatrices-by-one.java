class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] a = new int[n][n];
        for (int[] q : queries) {
            int r1 = q[0], r2 = q[2];
            int c1 = q[1], c2 = q[3];
            for (int i = r1; i <= r2; i++) 
                a[i][c1]++;
            
            if (c2 + 1 < n) 
                for (int i = r1; i <= r2; i++) 
                    a[i][c2 + 1]--;
        }

        for (int i = 0; i < n; i++) 
            for (int j = 1; j < n; j++) 
                a[i][j] += a[i][j - 1];

        return a;
    }
}