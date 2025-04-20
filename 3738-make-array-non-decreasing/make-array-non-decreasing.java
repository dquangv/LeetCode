class Solution {
    public int maximumPossibleSize(int[] nums) {
        int count = 0, lm = 0;
        for (int n : nums) {
            if (count == 0 || n >= lm) {
                lm = n;
                count++;
            }
        }

        return count;
    }
}