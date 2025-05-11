class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int odd = 0;
        for (int num : arr) {
            if (odd == 3) return true;
            odd++;
            if (num % 2 == 0) odd = 0;
        }

        if (odd == 3) return true;

        return false;
    }
}