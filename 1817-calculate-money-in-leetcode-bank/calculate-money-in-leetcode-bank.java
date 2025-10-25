class Solution {
    public int totalMoney(int n) {
        return 28 * (n / 7) + 7 * (n / 7) * (n / 7 - 1) / 2 + (2 * (n / 7) + n % 7 + 1) * (n % 7) / 2;
    }
}