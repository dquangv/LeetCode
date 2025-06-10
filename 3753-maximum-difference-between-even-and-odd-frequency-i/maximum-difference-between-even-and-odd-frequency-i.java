class Solution {
    public int maxDifference(String s) {
        int a1 = Integer.MIN_VALUE, a2 = Integer.MAX_VALUE;
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int f : freq) {
            if (f == 0) continue;
            if (f % 2 == 0) a2 = Math.min(a2, f);
            else a1 = Math.max(a1, f);
        }

        if (a1 == Integer.MIN_VALUE || a2 == Integer.MAX_VALUE) return -1;

        return a1 - a2;
    }
}