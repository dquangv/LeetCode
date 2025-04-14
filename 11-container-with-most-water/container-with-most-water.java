class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;

        while (left <= right) {
            int heightOfContainer = Math.min(height[left], height[right]);
            int widthOfContainer = right - left;

            result = Math.max(result, heightOfContainer * widthOfContainer);

            while (left <= right && height[left] <= heightOfContainer)
                left++;

            while (left <= right && height[right] <= heightOfContainer)
                right--;
        }

        return result;
    }
}