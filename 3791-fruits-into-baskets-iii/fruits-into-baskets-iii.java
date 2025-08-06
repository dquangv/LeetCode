class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = baskets.length;

        // Find the smallest power of 2 >= n to size the segment tree properly
        int N = 1;
        while (N <= n) N <<= 1; // Same as N = N * 2, until it's >= n

        // Create a segment tree of size 2*N to store basket capacities
        int[] segTree = new int[N << 1]; // segTree size = 2 * N

        // Fill leaf nodes (baskets) into the segment tree at indices N to N+n-1
        for (int i = 0; i < n; i++)
            segTree[N + i] = baskets[i];

        // Build the segment tree bottom-up
        for (int i = N - 1; i > 0; i--) 
            segTree[i] = Math.max(segTree[2 * i], segTree[2 * i + 1]);

        int count = 0; // To count how many fruits remain unplaced

        // Try placing each fruit one by one
        for (int i = 0; i < n; ++i) {
            int x = fruits[i]; // Current fruit's quantity
            int index = 1; // Start from the root of the segment tree

            // If no basket can hold this fruit, increment unplaced counter
            if (segTree[index] < x) {
                count++;
                continue;
            }

            // Traverse the segment tree to find the leftmost basket >= x
            while (index < N)
                // If the left child has a valid capacity, go left
                if (segTree[index * 2] >= x) 
                    index *= 2;
                else // Otherwise, go right
                    index = index * 2 + 1;

            // We found the appropriate basket at this leaf node
            segTree[index] = -1; // Mark basket as used (no longer usable)

            // Update the segment tree bottom-up to reflect this change
            while (index > 1) {
                index >>= 1; // Move to parent node
                segTree[index] = Math.max(segTree[2 * index], segTree[2 * index + 1]);
            }
        }

        return count; // Number of fruit types that couldn't be placed
    }
}
