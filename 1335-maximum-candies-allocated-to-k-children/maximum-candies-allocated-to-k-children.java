class Solution {
    public int maximumCandies(int[] candies, long k) {
        int left = 1;
        int right = (int) 1e7;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (isAllocated(candies, k, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private boolean isAllocated(int[] candies, long k, int mid) {
        long count = 0;

        for (int candy : candies) {
            count += candy / mid;
        }

        return count >= k;
    }
}