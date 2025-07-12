class Solution {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        // Always make left the smaller player number and right the larger
        int left = Math.min(firstPlayer, secondPlayer);
        int right = Math.max(firstPlayer, secondPlayer);

        // Base case: if players are already paired against each other
        if (left + right == n + 1)
            return new int[] { 1, 1 }; // They face in round 1

        // Base case: small n = 3 or 4 always meet in round 2
        if (n == 3 || n == 4)
            return new int[] { 2, 2 };

        // Mirror the players to the left half of the lineup (symmetry optimization)
        if (left - 1 > n - right) {
            int temp = n + 1 - left;
            left = n + 1 - right;
            right = temp;
        }

        int nextRound = (n + 1) / 2; // number of players in next round
        int minRound = n; // to track the earliest round they can meet
        int maxRound = 1; // to track the latest round they can meet

        // If both players are in the left half of the mirrored lineup
        if (right * 2 <= n + 1) {
            int preLeft = left - 1; // number of players before 'left'
            int midGap = right - left - 1; // number of players between 'left' and 'right'

            // Try all combinations of player wins between the two
            for (int i = 0; i <= preLeft; i++)
                for (int j = 0; j <= midGap; j++) {
                    // Recurse on the players' new positions in next round
                    int[] res = earliestAndLatest(nextRound, i + 1, i + j + 2);
                    minRound = Math.min(minRound, 1 + res[0]);
                    maxRound = Math.max(maxRound, 1 + res[1]);
                }
        } else {
            // If 'right' is in the mirrored right half
            int mirrored = n + 1 - right;
            int preLeft = left - 1;
            int midGap = mirrored - left - 1; // players between left and mirrored right
            int innerGap = right - mirrored - 1; // players between mirrored and actual right

            for (int i = 0; i <= preLeft; i++)
                for (int j = 0; j <= midGap; j++) {
                    // Recursively compute rounds based on mirrored positions
                    int[] res = earliestAndLatest(nextRound,
                            i + 1,
                            i + j + 1 + (innerGap + 1) / 2 + 1);
                    minRound = Math.min(minRound, 1 + res[0]);
                    maxRound = Math.max(maxRound, 1 + res[1]);
                }
        }

        // Return the earliest and latest possible rounds they can meet
        return new int[] { minRound, maxRound };
    }
}
