class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int res = 0;

        // Sliding window: [left, right] with sum as the total fruits in window
        for (int left = 0, right = 0, sum = 0; right < fruits.length; right++) {
            sum += fruits[right][1]; // add fruits at current right position

            // If current window [fruits[left][0], fruits[right][0]] is too far,
            // shrink it from the left until it's valid
            while (left <= right && !isValidRange(fruits[left][0], fruits[right][0], startPos, k))
                sum -= fruits[left++][1]; // remove fruits on the left

            // update result with the max sum
            res = Math.max(res, sum);
        }

        return res;
    }

    // Check if we can reach all positions from leftPos to rightPos within k steps
    private boolean isValidRange(int leftPos, int rightPos, int startPos, int k) {
        if (rightPos <= startPos)
            // All fruit positions are to the left
            return startPos - leftPos <= k;
        else if (leftPos >= startPos)
            // All fruit positions are to the right
            return rightPos - startPos <= k;
        else {
            // Fruits are on both sides: go left then right or right then left
            int left = startPos - leftPos;
            int right = rightPos - startPos;
            // choose the cheaper path: go to one side first, then to the other
            return left <= right ? left * 2 + right <= k : right * 2 + left <= k;
        }
    }
}
