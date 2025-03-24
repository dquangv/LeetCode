class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        long busyDays = 0, preStart = -1, preEnd = -1;

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            if (preEnd < start) {
                if (preStart != -1) {
                    busyDays += preEnd - preStart + 1;
                }

                preStart = start;
                preEnd = end;
            } else {
                preEnd = Math.max(preEnd, end);
            }
        }

        if (preStart != -1) {
            busyDays += preEnd - preStart + 1;
        }

        return days - (int) busyDays;
    }
}