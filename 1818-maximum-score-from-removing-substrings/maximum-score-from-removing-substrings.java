class Solution {
    public int maximumGain(String s, int x, int y) {
        // Variables to track count of 'a' and 'b' based on which pair has more value
        int minCharCount = 0, maxCharCount = 0;

        // Determine which substring ('ab' or 'ba') has higher value
        int maxVal = x > y ? x : y;
        int minVal = x > y ? y : x;

        // Set the characters based on which operation is more valuable
        // If "ab" is more valuable, then maxChar = 'a' and minChar = 'b'
        // If "ba" is more valuable, then maxChar = 'b' and minChar = 'a'
        char maxChar = x > y ? 'a' : 'b';
        char minChar = x > y ? 'b' : 'a';

        int result = 0; // To store total points earned

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            // Case when current character is the one that completes a valuable pair
            if (currChar == minChar) {
                if (maxCharCount > 0) {
                    // Found a valuable pair (either "ab" or "ba")
                    result += maxVal;
                    maxCharCount--; // Use one maxChar to form the pair
                } else
                    // Store the minChar as it cannot pair yet
                    minCharCount++;
            }
            // Case when current character is the one that begins a valuable pair
            else if (currChar == maxChar)
                maxCharCount++;
            // If current character is neither 'a' nor 'b', reset counts and settle remaining pairs
            else {
                // Process remaining min/max chars to form less valuable pairs
                result += Math.min(maxCharCount, minCharCount) * minVal;
                // Reset counts for new segment
                maxCharCount = 0;
                minCharCount = 0;
            }
        }

        // Handle remaining counts at the end of string
        result += Math.min(maxCharCount, minCharCount) * minVal;

        return result;
    }
}
