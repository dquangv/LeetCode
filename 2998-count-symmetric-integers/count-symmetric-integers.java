class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int result = 0;

        for (int i = low; i <= high; i++) {
            if (i < 100 && i % 10 == i / 10) {
                result++;
            } else if (i > 999) {
                int d1 = i / 1000;
                int d2 = (i / 100) % 10;
                int d3 = (i / 10) % 10;
                int d4 = i % 10;

                if (d1 + d2 == d3 + d4) {
                    result++;
                }
            }
        }

        return result;
    }
}