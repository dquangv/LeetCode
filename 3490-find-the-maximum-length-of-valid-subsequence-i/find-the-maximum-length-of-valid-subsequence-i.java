class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        if (n == 2) return 2;

        // z: parity of first number (true for odd, false for even)
        boolean z = (nums[0] & 1) == 1;

        // len[0]: length of even parity subsequence
        // len[1]: length of odd parity subsequence
        // len[2]: length of alternating parity subsequence (starting with nums[0])
        int[] len = new int[3];
        len[z ? 1 : 0] = 1; // initialize correct parity count
        len[2] = 1;         // initialize alternating parity count

        for (int i = 1; i < n; i++) {
            boolean x = (nums[i] & 1) == 1; // parity of current number

            // Increase the count of current parity
            len[x ? 1 : 0]++;

            // Check if we can extend the alternating parity sequence
            if (x != z) {
                len[2]++;
                z = !z; // flip parity for alternating pattern
            }
        }

        // Return the maximum among even-only, odd-only, and alternating parity subsequences
        return Math.max(len[0], Math.max(len[1], len[2]));
    }
}
