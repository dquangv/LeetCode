class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long result = 0;
        int imax = 0, dmax = 0;

        for (int num : nums) {
            result = Math.max(result, (long) dmax * num);
            dmax = Math.max(dmax, imax - num);
            imax = Math.max(imax, num);
        }

        return result;
    }
}