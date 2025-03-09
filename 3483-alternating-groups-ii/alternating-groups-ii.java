class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;

        int[] arr = new int[n + k - 1];
        for (int i = 0; i < n; i++) {
            arr[i] = colors[i];
        }

        for (int i = 0; i < k - 1; i++) {
            arr[i + n] = colors[i];
        }

        int nonAlternating = 0;
        for (int i = 0; i < k - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                nonAlternating++;
            }
        }

        int count = (nonAlternating == 0) ? 1 : 0;

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] == arr[i]) {
                nonAlternating--;
            }

            if (arr[i + k - 2] == arr[i + k - 1]) {
                nonAlternating++;
            }

            if (nonAlternating == 0) {
                count++;
            }
        }

        return count;
    }
}