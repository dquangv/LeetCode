class Solution {
    public int maxFreqSum(String s) {
        // Frequency map
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray())
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

        int maxConsonant = 0;
        int maxVowel = 0;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            char ch = entry.getKey();
            int f = entry.getValue();

            if (vowels.contains(ch))
                maxVowel = Math.max(maxVowel, f);
            else
                maxConsonant = Math.max(maxConsonant, f);
        }

        return maxConsonant + maxVowel;
    }
}