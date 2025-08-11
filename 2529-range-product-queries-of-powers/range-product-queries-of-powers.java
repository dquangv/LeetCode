class Solution {
    public int[] productQueries(int n, int[][] queries) {
        int MOD = 1_000_000_007;
        List<Integer> powers = new ArrayList<>(); // to store powers of 2 present in n

        // Step 1: Build the 'powers' array by checking each bit of n
        // If the ith bit in n is set, then (1 << i) is part of the sum decomposition
        for (int i = 0; i < 32; i++)
            if ((n & (1 << i)) != 0)
                powers.add(1 << i); // store this power of two

        int[] ans = new int[queries.length]; // result array for all queries

        // Step 2: Process each query
        for (int qIndex = 0; qIndex < queries.length; qIndex++) {
            int left = queries[qIndex][0];
            int right = queries[qIndex][1];

            // Start with the first element in the range
            long product = powers.get(left);

            // Multiply all elements from left+1 to right
            for (int i = left + 1; i <= right; i++)
                product = (product * powers.get(i)) % MOD; // take modulo to avoid overflow

            // Store result in the answer array
            ans[qIndex] = (int) product;
        }

        return ans; // return all query results
    }
}