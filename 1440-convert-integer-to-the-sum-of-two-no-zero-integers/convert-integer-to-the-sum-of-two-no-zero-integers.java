class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++)
            if (isNoZero(a) && isNoZero(n - a))
                return new int[] { a, n - a };

        return new int[] {};
    }

    private boolean isNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0)
                return false;
            num /= 10;
        }
        
        return true;
    }
}
