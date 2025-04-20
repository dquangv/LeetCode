class Solution {
    public int numRabbits(int[] answers) {
        int[] maxArr = new int[1001];
        int result = 0;

        for (int ans : answers) {
            maxArr[ans]++;
        }

        boolean[] check = new boolean[1001];

        for (int i = 0; i < 1001; i++) {
            if (maxArr[i] > 0 && !check[i]) {
                int groupSize = i + 1;
                int numGroups = (maxArr[i] + groupSize - 1) / groupSize;
                result += numGroups * groupSize;
                check[i] = true;
            }
        }

        return result;
    }
}