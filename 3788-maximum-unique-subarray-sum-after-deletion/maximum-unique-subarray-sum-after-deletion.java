class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int sum = 0;
        boolean hasNonNegative = false;

        for (int num : nums)
            if (num >= 0) {
                hasNonNegative = true;
                if (!seen.contains(num)) {
                    seen.add(num);
                    sum += num;
                }
            }

        if (hasNonNegative)
            return sum;
        else {
            int maxNegative = nums[0];
            for (int num : nums)
                if (num > maxNegative)
                    maxNegative = num;

            return maxNegative;
        }
    }
}