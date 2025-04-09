class Solution {
    public int minOperations(int[] nums, int k) {
        boolean[] arr = new boolean[101];
        int result = 0;
        arr[k] = true;

        for (int num : nums) {
            if (num < k) return -1;
            if (!arr[num]) {
                arr[num] = true;
                result++;
            }
        }

        return result;
    }
}