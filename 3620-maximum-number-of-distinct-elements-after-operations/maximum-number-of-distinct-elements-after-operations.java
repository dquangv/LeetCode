class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int prevMax = Integer.MIN_VALUE;
        int distinct = 0;

        for (int num : nums) {
            int lowerBound = num - k;
            int upperBound = num + k;

            if (prevMax < lowerBound) {
                prevMax = lowerBound;
                distinct++;
            } else if (prevMax < upperBound) {
                prevMax++;
                distinct++;
            }
        }

        return distinct;
    }
}