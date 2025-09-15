class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> broken = new HashSet<>();
        for (char c : brokenLetters.toCharArray())
            broken.add(c);

        int count = 0;
        String[] words = text.split(" ");
        for (String w : words) {
            boolean valid = true;
            for (char c : w.toCharArray())
                if (broken.contains(c)) {
                    valid = false;
                    break;
                }

            if (valid)
                count++;
        }

        return count;
    }
}