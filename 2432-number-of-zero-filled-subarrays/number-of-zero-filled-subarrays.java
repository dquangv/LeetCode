class Solution {
    public long zeroFilledSubarray(int[] nums) {

        long count = 0; // current streak of continuous zeros
        long total = 0;

        for (int i = 0; i < nums.length; i++)
            if (nums[i] == 0) {
                count++; // extend the streak of zeros
                total += count; // add all subarrays ending at i
                                // e.g. for 3 zeros → [0], [0,0], [0,0,0]
            } else
                // reset streak when encountering a non-zero
                count = 0;

        return total;
    }
}
