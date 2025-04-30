class Solution {
    public int findNumbers(int[] nums) {
        int result = 0;

        for (int num : nums) {
            if (num == 100000 || (num < 10000 && num > 999) || (num < 100 && num > 9)) result++;
        }

        return result;
    }
}