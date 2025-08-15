class Solution {
    public boolean isPowerOfFour(int n) {
        if (n < 1)
            return false;

        for (; n > 1; n /= 4)
            if (n % 4 != 0)
                return false;

        return n == 1;
    }
}