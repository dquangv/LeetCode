func maxArea(height []int) int {
    left, right, result := 0, len(height) - 1, 0;

    for left <= right {
        heightOfContainer := min(height[left], height[right]);
        result = max(result, heightOfContainer * (right - left));

        for left <= right && height[left] <= heightOfContainer {
            left++;
        }

        for left <= right && height[right] <= heightOfContainer {
            right--;
        }
    }

    return result;
}

func min(a, b int) int {
    if a < b  {
        return a;
    }

    return b;
}

func max(a, b int) int {
    if a < b {
        return b;
    }

    return a;
}