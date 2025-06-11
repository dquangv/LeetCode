class Solution {
    public int maxDifference(String s, int k) {
        int n = s.length();

        // Mảng preSum[i][d] lưu tổng số lần xuất hiện của chữ số d trong đoạn s[0..i-1]
        int[][] preSum = new int[n + 1][5]; // chỉ xét các chữ số từ 0 đến 4

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= 4; ++j)
                preSum[i + 1][j] = preSum[i][j]; // sao chép lại dữ liệu

            preSum[i + 1][s.charAt(i) - '0']++; // tăng số lần xuất hiện của chữ số hiện tại
        }

        int max = Integer.MIN_VALUE;

        // Xét tất cả cặp chữ số a, b khác nhau
        for (int a = 0; a <= 4; ++a)
            for (int b = 0; b <= 4; ++b) {
                if (a == b)
                    continue;

                int left = 0, right = 0;

                // min[p][q] lưu giá trị nhỏ nhất của (số lần a - số lần b) tại vị trí left
                // với p là chẵn/lẻ của a và q là chẵn/lẻ của b
                int[][] min = new int[][] {
                        { Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2 },
                        { Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2 }
                };

                while (right < n) {
                    // di chuyển left sao cho thỏa mãn:
                    // 1. độ dài đoạn >= k
                    // 2. số lần xuất hiện của a và b trong đoạn đều tăng
                    while (right - left + 1 >= k &&
                            preSum[right + 1][a] > preSum[left][a] &&
                            preSum[right + 1][b] > preSum[left][b]) {

                        // p: chẵn/lẻ của số lần a tại left
                        // q: chẵn/lẻ của số lần b tại left
                        int p = preSum[left][a] % 2;
                        int q = preSum[left][b] % 2;
                        min[p][q] = Math.min(min[p][q], preSum[left][a] - preSum[left][b]);

                        ++left;
                    }

                    // x: chẵn/lẻ ngược với số lần a tại right + 1
                    // y: chẵn/lẻ của số lần b tại right + 1
                    int x = preSum[right + 1][a] % 2 ^ 1;
                    int y = preSum[right + 1][b] % 2;

                    // cập nhật max dựa trên công thức:
                    // (tổng a - tổng b tại right + 1) - (min tổng a - tổng b ở left phù hợp điều kiện)
                    max = Math.max(max, preSum[right + 1][a] - preSum[right + 1][b] - min[x][y]);

                    ++right;
                }
            }

        return max;
    }
}
