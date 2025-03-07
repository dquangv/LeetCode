class Solution {
    public int[] closestPrimes(int left, int right) {
        if (right - left < 1 || right < 2) {
            return new int[] { -1, -1 };
        }

        boolean[] isNotPrime = new boolean[right + 1];
        generatePrimes(right, isNotPrime);

        int prevPrime = -1;
        int[] result = new int[] { -1, -1 };
        int minDiff = Integer.MAX_VALUE;

        for (int i = Math.max(2, left); i <= right; i++) {
            if (!isNotPrime[i]) {

                if (prevPrime != -1) {
                    int diff = i - prevPrime;

                    if (diff < minDiff) {
                        minDiff = diff;
                        result[0] = prevPrime;
                        result[1] = i;

                        if (minDiff == 2)
                            break;
                    }
                }

                prevPrime = i;
            }
        }

        return result;
    }

    private void generatePrimes(int n, boolean[] isNotPrime) {
        isNotPrime[0] = isNotPrime[1] = true;
        
        for (int i = 2; i * i <= n; i++) {

            if (!isNotPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}