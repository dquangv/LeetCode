class Solution {
    public boolean isPalindrome(int x) {
        String xStr = String.valueOf(x);
        int n = xStr.length();
        for (int i = 0; i < n / 2; i++) {
            if (xStr.charAt(i) != xStr.charAt(n - 1 - i)) return false;
        }

        return true;
    }
}