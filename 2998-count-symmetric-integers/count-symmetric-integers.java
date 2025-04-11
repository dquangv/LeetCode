class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            int digits = numDigits(i);
            if (digits % 2 == 0 && isSymmetric(i, digits)) {
                count++;
            }
        }

        return count;
    }

    private boolean isSymmetric(int num, int digits) {
        int half = digits / 2;
        int sum1 = 0, sum2 = 0;
        int x = num;

        for (int i = 0; i < half; i++) {
            sum1 += x % 10;
            x /= 10;
        }

        for (int i = 0; i < half; i++) {
            sum2 += x % 10;
            x /= 10;
        }

        return sum1 == sum2;
    }

    private int numDigits(int num) {
        return (int) Math.log10(num) + 1;
    }
}