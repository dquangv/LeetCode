class Solution {
    public int longestPalindrome(String[] words) {
        int[][] count = new int[26][26];

        for (String word : words) {
            int a = word.charAt(0) - 'a';
            int b = word.charAt(1) - 'a';
            count[a][b]++;
        }

        int length = 0;
        boolean hasCenter = false;

        for (int i = 0; i < 26; i++)
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    length += (count[i][i] / 2) * 4;
                    if (count[i][i] % 2 == 1)
                        hasCenter = true;
                } else if (i < j) {
                    int pairs = Math.min(count[i][j], count[j][i]);
                    length += pairs * 4;
                }
            }

        if (hasCenter)
            length += 2;

        return length;
    }
}