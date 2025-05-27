class Solution {
    public int differenceOfSums(int n, int m) {
        int num2 = m <= n ? m : 0, i = 2;
        while (m * i <= n) {
            num2 += m * i;
            i++;
        }

        return n * (n + 1) / 2 - num2 * 2;
    }
}