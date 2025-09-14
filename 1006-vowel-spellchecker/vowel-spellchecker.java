class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        // Exact match set
        Set<String> exact = new HashSet<>();
        // Case-insensitive map
        Map<String, String> lower = new HashMap<>();
        // Vowel-error map
        Map<String, String> vow = new HashMap<>();

        // Preprocess wordlist
        for (String w : wordlist) {
            exact.add(w);
            String lw = lowerIt(w);
            lower.putIfAbsent(lw, w);
            vow.putIfAbsent(devowel(lw), w);
        }

        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exact.contains(q))
                // Case 1: Exact match
                res[i] = q;
            else if (lower.containsKey(lowerIt(q)))
                // Case 2: Case-insensitive
                res[i] = lower.get(lowerIt(q));
            else if (vow.containsKey(devowel(lowerIt(q))))
                // Case 3: Vowel error
                res[i] = vow.get(devowel(lowerIt(q)));
            else
                // Case 4: No match
                res[i] = "";
        }

        return res;
    }

    // Helper: lowercase
    private String lowerIt(String s) {
        return s.toLowerCase();
    }

    // Helper: devowel (replace vowels with '#')
    private String devowel(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray())
            if ("aeiou".indexOf(c) >= 0)
                sb.append('#');
            else
                sb.append(c);

        return sb.toString();
    }
}