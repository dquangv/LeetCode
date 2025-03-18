class Solution {
    public int longestNiceSubarray(int[] nums) {
        int left = 0, usedBits = 0, maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            while ((usedBits & nums[right]) != 0) {
                usedBits ^= nums[left];
                left++;
            }

            usedBits |= nums[right];

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}