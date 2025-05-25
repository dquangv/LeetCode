class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        int length = 0;
        boolean hasCenter = false;

        for (String word : freq.keySet()) {
            String rev = new StringBuilder(word).reverse().toString();
            int count = freq.get(word);

            if (!word.equals(rev)) {
                if (freq.containsKey(rev)) {
                    int pairCount = Math.min(count, freq.get(rev));
                    length += pairCount * 4;
                    freq.put(word, count - pairCount);
                    freq.put(rev, freq.get(rev) - pairCount);
                }
            } else {
                length += (count / 2) * 4;
                if (count % 2 == 1) hasCenter = true;
            }
        }

        if (hasCenter) length += 2;

        return length;
    }
}