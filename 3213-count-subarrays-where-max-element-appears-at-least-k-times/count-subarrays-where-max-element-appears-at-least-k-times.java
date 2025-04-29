class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        long result = 0;
        int count = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == maxVal) {
                count++;
            }

            while (count >= k) {
                if (nums[left] == maxVal) {
                    count--;
                }
                
                left++;
            }

            result += left;
        }

        return result;
    }
}