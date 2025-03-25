import java.util.Arrays;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        for (int k = 0; k < 2; k++) {
            final int index = k;
            Arrays.sort(rectangles, (a, b) -> Integer.compare(a[index], b[index]));

            boolean first = false;
            int end = rectangles[0][k + 2];

            for (int[] rect : rectangles) {
                if (rect[k] >= end) {
                    if (first) {
                        return true;
                    }

                    first = true;
                }
                
                end = Math.max(end, rect[k + 2]);
            }
        }

        return false;
    }
}
