class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);

        return count(nums, upper + 1) - count(nums, lower);
    }

    public long count(int[] nums, int x) {
        int left = 0, right = nums.length - 1;
        long result = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum < x) {
                result += (right - left);
                left++;
            } else right--;
        }

        return result;
    }
}