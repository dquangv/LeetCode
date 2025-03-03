class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] result = new int[nums.length];
        int i = 0;

        for (int num : nums) {
            if (num < pivot) {
                result[i] = num;
                i++;
            }
        }

        for (int num : nums) {
            if (num == pivot) {
                result[i] = num;
                i++;
            }
        }

        for (int num : nums) {
            if (num > pivot) {
                result[i] = num;
                i++;
            }
        }

        return result;
    }
}