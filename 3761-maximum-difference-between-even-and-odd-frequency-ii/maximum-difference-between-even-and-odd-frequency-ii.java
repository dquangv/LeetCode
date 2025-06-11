public class Solution {
    public int maxDifference(String s, int k) {
        int n = s.length();
        int[][] pre = new int[5][n]; // prefix sum of counts for digits 0..4
        int[][] closestRight = new int[5][n]; // index of the next occurrence of digit x from position i

        // Precompute prefix sums for each digit 0-4
        for (int x = 0; x < 5; x++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) - '0' == x)
                    count++;
                pre[x][i] = count;
            }
        }

        // Precompute the closest right occurrence of each digit from each position
        for (int x = 0; x < 5; x++) {
            int nextPos = n;
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) - '0' == x)
                    nextPos = i;
                closestRight[x][i] = nextPos;
            }
        }

        int best = Integer.MIN_VALUE;

        for (int odd = 0; odd < 5; odd++) {
            for (int even = 0; even < 5; even++) {
                if (odd == even)
                    continue;

                // Suffix max diff arrays
                int[][][] suf = new int[2][2][n];
                for (int p = 0; p < 2; p++)
                    for (int q = 0; q < 2; q++)
                        for (int i = 0; i < n; i++)
                            suf[p][q][i] = Integer.MIN_VALUE;

                for (int i = 0; i < n; i++) {
                    int oddCount = pre[odd][i];
                    int evenCount = pre[even][i];
                    if (oddCount > 0 && evenCount > 0) {
                        int p = oddCount % 2;
                        int q = evenCount % 2;
                        int diff = oddCount - evenCount;
                        suf[p][q][i] = diff;
                    }
                }

                // Build suffix max arrays
                for (int p = 0; p < 2; p++)
                    for (int q = 0; q < 2; q++)
                        for (int i = n - 2; i >= 0; i--)
                            suf[p][q][i] = Math.max(suf[p][q][i], suf[p][q][i + 1]);

                // Now try each possible start of substring of length >= k
                for (int start = 0; start <= n - k; start++) {
                    int end = start + k - 1;
                    int oddBelow = (start > 0) ? pre[odd][start - 1] : 0;
                    int evenBelow = (start > 0) ? pre[even][start - 1] : 0;

                    int goodOddParity = (oddBelow + 1) % 2;
                    int goodEvenParity = evenBelow % 2;

                    int q1 = end;
                    int q2 = closestRight[odd][start];
                    int q3 = closestRight[even][start];
                    int queryIdx = Math.max(Math.max(q1, q2), q3);

                    if (queryIdx >= n)
                        continue;

                    int candidate = suf[goodOddParity][goodEvenParity][queryIdx];
                    if (candidate != Integer.MIN_VALUE) {
                        int adjusted = candidate - oddBelow + evenBelow;
                        best = Math.max(best, adjusted);
                    }
                }
            }
        }

        return best == Integer.MIN_VALUE ? -1 : best;
    }
}
