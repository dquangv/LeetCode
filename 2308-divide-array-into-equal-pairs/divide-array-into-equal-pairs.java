class Solution {
    public boolean divideArray(int[] nums) {
        int[] pairs = new int[501];

        for (int num : nums) {
            pairs[num]++;
        }

        for (int pair : pairs) {
            if ((pair & 1) != 0) return false;
        }

        return true;
    }
}