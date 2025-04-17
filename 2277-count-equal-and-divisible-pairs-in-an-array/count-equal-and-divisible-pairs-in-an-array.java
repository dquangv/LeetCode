class Solution {
    public int countPairs(int[] nums, int k) {
        return recursion(nums, k, 0);
    }

    public int recursion(int[] nums, int k, int i) {
        if (i > nums.length - 2) return 0;

        int count = 0;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] == nums[j] && (i * j) % k == 0) count++; 
        }

        return count + recursion(nums, k, i + 1);
    }
}