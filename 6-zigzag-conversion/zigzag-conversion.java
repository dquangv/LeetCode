class Solution {
    public String convert(String s, int numRows) {
        // If there's only one row, the zigzag is just the string itself.
        if (numRows == 1)
            return s;

        // This will store the final converted zigzag string.
        StringBuilder a = new StringBuilder();

        // Loop through each row
        for (int i = 0; i < numRows; i++)
            // Start from the i-th character and jump in steps
            for (int j = i; j < s.length(); j += (2 * (numRows - 1))) {
                // Always add the vertical elements (same column in zigzag)
                a.append(s.charAt(j));

                // For rows in the middle (not first or last),
                // also add the diagonal elements between vertical columns
                if (i > 0 && i < numRows - 1) {
                    // Calculate the index of the diagonal character
                    int diagIndex = j + (2 * (numRows - 1)) - (2 * i);

                    // Make sure the diagonal index is still within bounds
                    if (diagIndex < s.length())
                        a.append(s.charAt(diagIndex));
                }
            }

        // Return the final zigzag string
        return a.toString();
    }
}
