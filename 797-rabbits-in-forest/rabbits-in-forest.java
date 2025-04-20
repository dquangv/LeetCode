class Solution {
    public int numRabbits(int[] answers) {
        int[] maxArr = new int[1001];
        int result = 0;

        for (int ans : answers) {
            if (++maxArr[ans] == 1) result += ans + 1;
            if (maxArr[ans] == ans + 1) maxArr[ans] = 0;
        }

        return result;
    }
}