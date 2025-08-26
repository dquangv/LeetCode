class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxDiag = -1; // store maximum diagonal squared
        int maxArea = -1; // store maximum area
        int res = 0; // result area

        for (int[] rect : dimensions) {
            int l = rect[0], w = rect[1];
            int diag = l * l + w * w;
            int area = l * w;

            // Choose rectangle with longer diagonal,
            // or if equal diagonals, choose larger area
            if (diag > maxDiag || (diag == maxDiag && area > maxArea)) {
                maxDiag = diag;
                maxArea = area;
                res = area;
            }
        }
        
        return res;
    }
}
