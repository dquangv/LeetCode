class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;

        // Max-heap for the first part (smallest sum of largest n elements)
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a);
        // Min-heap for the second part (largest sum of smallest n elements)
        PriorityQueue<Integer> right = new PriorityQueue<>();

        long[] minLeft = new long[3 * n]; // Stores minimum possible sum for left part

        long leftSum = 0;
        long rightSum = 0;

        // Build initial left heap using first n elements
        for (int i = 0; i < n; i++) {
            leftSum += nums[i];
            left.add(nums[i]);
        }
        minLeft[n - 1] = leftSum;

        // From index n to 2n-1, keep only the smallest n elements in sum (i.e., discard largest)
        for (int i = n; i < 2 * n; i++) {
            leftSum += nums[i];
            left.add(nums[i]);
            leftSum -= left.poll();  // Remove largest element to keep only smallest n
            minLeft[i] = leftSum;
        }

        // Build initial right heap using last n elements
        for (int i = 3 * n - 1; i >= 2 * n; i--) {
            rightSum += nums[i];
            right.add(nums[i]);
        }

        // Initial answer: difference between left sum and right sum
        long res = minLeft[2 * n - 1] - rightSum;

        // Try shifting the right boundary to the left from 2n-1 to n
        for (int i = 2 * n - 1; i >= n; i--) {
            rightSum += nums[i];
            right.add(nums[i]);
            rightSum -= right.poll();  // Remove smallest to keep only largest n
            res = Math.min(res, minLeft[i - 1] - rightSum);
        }

        return res;
    }
}
