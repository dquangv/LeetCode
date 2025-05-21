class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRow = false, firstCol = false;

        // Bước 1: Kiểm tra hàng và cột đầu tiên
        for (int i = 0; i < m; i++)
            if (matrix[i][0] == 0)
                firstCol = true;

        for (int j = 0; j < n; j++)
            if (matrix[0][j] == 0)
                firstRow = true;

        // Bước 2: Đánh dấu trong hàng đầu và cột đầu
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Bước 3: Set về 0 nếu bị đánh dấu
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;

        // Bước 4: Set lại hàng đầu và cột đầu nếu cần
        if (firstCol)
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;

        if (firstRow)
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;

    }
}