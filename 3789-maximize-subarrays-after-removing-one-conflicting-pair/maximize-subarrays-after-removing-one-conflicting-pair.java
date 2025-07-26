class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // Step 1: Build conflict list where conflicts[i] contains all values j such that (j, i) is a conflicting pair and j < i
        List<List<Integer>> conflicts = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            conflicts.add(new ArrayList<>());

        for (int[] c : conflictingPairs) {
            int left = c[0], right = c[1];
            // Ensure left < right for consistency
            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }
            // Register this pair as a conflict with 'right' depending on 'left'
            conflicts.get(right).add(left);
        }

        // Step 2: Prepare to compute total subarrays and restrict data
        long[] restrictRemoval = new long[n + 1]; // restrictRemoval[i]: gain in subarrays if we remove conflict involving i
        int leftMaxRestrict = 0, leftSecondMaxRestrict = 0; // track the 2 most restrictive positions (left ends of conflict pairs)
        long res = 0L;

        // Step 3: Iterate through the array from 1 to n
        for (int i = 1; i <= n; i++) {
            for (Integer ele : conflicts.get(i))
                // Update top 2 largest conflicting left ends for current i
                if (ele > leftMaxRestrict) {
                    leftSecondMaxRestrict = leftMaxRestrict;
                    leftMaxRestrict = ele;
                } else if (ele > leftSecondMaxRestrict)
                    leftSecondMaxRestrict = ele;

            // For index i, we can form (i - leftMaxRestrict) subarrays ending at i that do not contain any full conflicting pair
            res += (long) (i - leftMaxRestrict);

            // Store potential gain if we remove the conflict (leftMaxRestrict, i)
            restrictRemoval[leftMaxRestrict] += (long) (leftMaxRestrict - leftSecondMaxRestrict);
        }

        // Step 4: Find the best conflicting pair to remove (maximize gain in subarrays)
        long maxRemovalVal = 0L;
        for (int i = 1; i <= n; i++)
            maxRemovalVal = Math.max(maxRemovalVal, restrictRemoval[i]);

        // Step 5: Add the gain from removing the best conflicting pair
        res += maxRemovalVal;
        return res;
    }
}
