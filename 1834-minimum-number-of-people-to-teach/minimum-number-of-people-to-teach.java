class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // Convert each user's languages into a Set for fast lookup
        List<Set<Integer>> userLang = new ArrayList<>();
        for (int i = 0; i < languages.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int lang : languages[i]) 
                set.add(lang);
                
            userLang.add(set);
        }

        Set<Integer> dontspeak = new HashSet<>();

        // Step 1: Find pairs that cannot communicate
        for (int[] fr : friendships) {
            int u = fr[0] - 1, v = fr[1] - 1; // 0-based index
            boolean share = false;
            for (int lang : userLang.get(u)) 
                if (userLang.get(v).contains(lang)) {
                    share = true;
                    break;
                }

            if (!share) {
                dontspeak.add(u);
                dontspeak.add(v);
            }
        }

        if (dontspeak.isEmpty()) 
            return 0;

        // Step 2: Count how many in dontspeak already know each language
        int[] langCount = new int[n + 1];
        for (int f : dontspeak) 
            for (int lang : userLang.get(f)) 
                langCount[lang]++;

        // Step 3: Find the best language
        int maxKnown = 0;
        for (int c : langCount) 
            maxKnown = Math.max(maxKnown, c);

        return dontspeak.size() - maxKnown;
    }
}