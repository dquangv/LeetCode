class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();

        for (int a = 0; a + 2 * k - 1 < n; a++)
            if (isStrictlyIncreasing(nums, a, a + k - 1) &&
                    isStrictlyIncreasing(nums, a + k, a + 2 * k - 1)) 
                return true;

        return false;
    }

    boolean isStrictlyIncreasing(List<Integer> nums, int l, int r) {
        for (int i = l + 1; i <= r; i++) 
            if (nums.get(i) <= nums.get(i - 1)) 
                return false;

        return true;
    }
}