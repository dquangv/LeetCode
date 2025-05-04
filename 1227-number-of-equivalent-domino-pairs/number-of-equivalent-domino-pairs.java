class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        // count = [0,0,...,0]
        int[] count = new int[100]; // max key [9,9] = 99
        int result = 0;

        for (int[] domino : dominoes) {
            int i = domino[0], j = domino[1];
            // [1,2] = [2,1] = 12
            int key = i < j ? i * 10 + j : j * 10 + i;
            result += count[key];
            count[key]++;
        }

        return result;
    }
}