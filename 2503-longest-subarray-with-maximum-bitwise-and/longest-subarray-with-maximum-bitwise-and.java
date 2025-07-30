class Solution {
    public int longestSubarray(int[] nums) {
        // Step 1: Find the maximum value in the array.
        // Only subarrays where all elements are equal to this value can have the maximum bitwise AND.
        int maxBitwiseAnd = Integer.MIN_VALUE;
        for (int num : nums)
            maxBitwiseAnd = Math.max(maxBitwiseAnd, num);

        // Step 2: Find the longest contiguous subarray where all elements are equal to maxBitwiseAnd
        int maxi = 1; // To store the maximum length found (at least 1)
        int count = 0; // Temporary counter for current segment
        int i = 0; // Index pointer

        // Step 3: Traverse through the array
        while (i < nums.length)
            // If current number equals the maxBitwiseAnd
            if (nums[i] == maxBitwiseAnd) {
                // Count how long the streak of maxBitwiseAnd continues
                while (i < nums.length && nums[i++] == maxBitwiseAnd)
                    count++;

                // Update the result if this streak is longer
                maxi = Math.max(maxi, count);
                count = 0; // Reset count for the next potential segment
            } else
                i++; // Move forward if not matching maxBitwiseAnd

        return maxi;
    }
}
