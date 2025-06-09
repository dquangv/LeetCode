class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1; // Bắt đầu từ 1
        k--; // Vì đã xét curr = 1 là vị trí đầu tiên

        while (k > 0) {
            long steps = countSteps(n, curr, curr + 1);
            if (steps <= k) {
                // Bỏ cả cây con curr → nhảy sang cây tiếp theo
                k -= steps;
                curr++;
            } else {
                // Vẫn nằm trong cây con curr → đi sâu hơn vào tầng dưới
                k--;
                curr *= 10;
            }
        }

        return curr;
    }

    // Đếm số bước từ prefix `n1` đến prefix `n2` (không vượt quá n)
    private long countSteps(int n, long n1, long n2) {
        long steps = 0;
        while (n1 <= n) {
            steps += Math.min(n + 1L, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }
}