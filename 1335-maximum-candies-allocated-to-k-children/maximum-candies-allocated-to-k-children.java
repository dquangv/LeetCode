class Solution {
    public int maximumCandies(int[] candies, long k) {
        int maxCandiesPerChild = 0;
        int left = 0;
        int right = 1;

        for (int candyPile : candies) {
            if (candyPile > right) {
                right = candyPile;
            }
        }
        
        right++;

        while (right - left > 1) {
            int mid = (right + left) / 2;
            long countChildren = 0;

            for (int candyPile : candies) {
                countChildren += candyPile / mid;
            }

            if (countChildren < k) {
                right = mid;
            } else {
                maxCandiesPerChild = mid;
                left = mid;
            }
        }

        return maxCandiesPerChild;
    }
}