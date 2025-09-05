class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 60; k++) {
            long x = (long) num1 - (long) num2 * k;

            // If x < k, impossible
            if (x < k)
                return -1;

            // Count set bits (popcount)
            if (Long.bitCount(x) <= k)
                return k;
        }
        
        return -1;
    }
}