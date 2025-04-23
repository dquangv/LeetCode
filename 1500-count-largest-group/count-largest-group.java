class Solution {
    public int countLargestGroup(int n) {
        int[] digitSumCount = new int[37];
        int maxGroupSize = 0;

        for (int i = 1; i <= n; i++) {
            int sum = digitSum(i);
            digitSumCount[sum]++;
            maxGroupSize = Math.max(maxGroupSize, digitSumCount[sum]);
        }

        int result = 0;
        for (int count : digitSumCount) {
            if (count == maxGroupSize) {
                result++;
            }
        }

        return result;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        
        return sum;
    }
}