class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long count1 = 0, count2 = 0, sum1 = 0, sum2 = 0;

        for (int num : nums1) {
            count1 += num == 0 ? 1 : 0;
            sum1 += num;
        }

        for (int num : nums2) {
            count2 += num == 0 ? 1 : 0;
            sum2 += num;
        }

        if ((count1 == 0 && sum1 < sum2 + count2) || (count2 == 0 && sum2 < sum1 + count1)) return -1;

        return Math.max(sum1 + count1, sum2 + count2);
    }
}