class Solution {
    public long minCost(int[] A, int[] B) {
        // TreeMap to store the frequency difference of each fruit cost
        TreeMap<Integer, Integer> count = new TreeMap<>();

        // Increase count for each fruit in basket1
        for (int a : A)
            count.merge(a, 1, Integer::sum);

        // Decrease count for each fruit in basket2
        for (int a : B)
            count.merge(a, -1, Integer::sum);

        List<Integer> swaps = new ArrayList<>(); // List to store extra fruits that need to be swapped
        long res = 0; // Total minimum cost
        long small = count.firstKey(); // The smallest fruit cost in either basket

        // Iterate through each fruit cost in sorted order
        for (int a : count.keySet()) {
            // If the difference in count is odd, impossible to balance baskets
            if (count.get(a) % 2 > 0)
                return -1;

            // We need half of the absolute difference as swap operations
            int v = Math.abs(count.get(a)) / 2;

            // Add the fruit value 'v' times to the swap list
            for (int i = 0; i < v; ++i)
                swaps.add(a);
        }

        // Only need to process half of the swaps because each swap fixes two mismatches
        for (int i = 0; i < swaps.size() / 2; ++i)
            // For each pair of mismatched fruits, choose the cheaper option:
            // Either directly swap the two fruits or swap via the smallest fruit twice
            res += Math.min(swaps.get(i), small * 2);

        return res; // Return the minimum total cost
    }
}