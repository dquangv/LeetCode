class Solution {
    public int countLargestGroup(int n) {
        int[] digitSumCount = new int[37], digitSum = new int[n + 1];
        int maxGroupSize = 0, result = 0;

        digitSumCount[0] = -1;
        
        for (int i = 1; i <= n; i++) {
            digitSum[i] = i % 10 + digitSum[i / 10];
            digitSumCount[digitSum[i]]++;
        }

        for (int group : digitSumCount) {
            if (maxGroupSize < group) {
                maxGroupSize = group;
                result = 0;
            }

            if (maxGroupSize == group) result++;
        }

        return result;
    }

}