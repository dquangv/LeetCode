class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int firstNumOfSub = nums[0], result = 0;

        for (int num : nums) {
            boolean diff = num - firstNumOfSub > k;
            if (diff) {
                result++;
                firstNumOfSub = num;
            }
        }

        return result + 1;
    }
}