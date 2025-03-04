class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 1) {
                n -= 1;
            } else if (n % 3 == 2) {
                return false;
            }

            n /= 3;
        }

        return true;
    }
}