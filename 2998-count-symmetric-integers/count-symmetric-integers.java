class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int result = 0;

        for (int i = low; i <= high; i++) {
            String s = String.valueOf(i);
            int n = s.length();

            if (n % 2 != 0) continue;

            int half = n / 2, sum1 = 0, sum2 = 0;

            for (int j = 0; j < half; j++) {
                sum1 += s.charAt(j);
                sum2 += s.charAt(j + half);
            }

            if (sum1 == sum2) result++;
        }

        return result;
    }
}