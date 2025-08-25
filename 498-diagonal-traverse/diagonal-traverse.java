class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int r = mat.length, c = mat[0].length;
        int[] ans = new int[r * c];
        int flip = 0, idx = 0;

        // Traverse through all diagonals
        for (int d = 0; d < r + c - 1; d++) {
            if (flip == 0) { // Going UP-RIGHT
                int i = Math.min(d, r - 1);
                int j = d - i;
                while (i >= 0 && j < c) {
                    ans[idx++] = mat[i][j];
                    i--;
                    j++;
                }
            } else { // Going DOWN-LEFT
                int j = Math.min(d, c - 1);
                int i = d - j;
                while (j >= 0 && i < r) {
                    ans[idx++] = mat[i][j];
                    i++;
                    j--;
                }
            }
            
            flip = 1 - flip; // toggle direction
        }

        return ans;
    }
}