class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = groups.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);
        int maxi = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (groups[i] != groups[j] &&
                        isHammingDistance(words[i], words[j]) &&
                        dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > maxi)
                maxi = dp[i];
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxi) {
                while (i != -1) {
                    result.add(words[i]);
                    i = parent[i];
                }
                break;
            }
        }
        
        Collections.reverse(result);
        return result;
    }

    public boolean isHammingDistance(String a, String b) {
        if (a.length() != b.length())
            return false;

        int difCount = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                difCount++;
                if (difCount > 1)
                    return false;
            }
        }

        return difCount == 1;
    }
}