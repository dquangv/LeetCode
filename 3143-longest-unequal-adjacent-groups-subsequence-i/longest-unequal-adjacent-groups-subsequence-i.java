class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<>();
        result.add(words[0]);
        int flag = groups[0];

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != flag) {
                result.add(words[i]);
                flag = groups[i];
            }
        }

        return result;
    }
}