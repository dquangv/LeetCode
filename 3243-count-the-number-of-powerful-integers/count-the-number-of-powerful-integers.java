class Solution {
    // Time Limit Exceeded (start = 123456, finish = 32486458654, limit = 4, s = "1")

    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    //     long suffix = Long.parseLong(s);

    //     if (finish < suffix)
    //         return 0;

    //     int lengthSuf = s.length();
    //     long powerOfTen = (long) Math.pow(10, lengthSuf);
    //     long prefixFinish = (long) finish / powerOfTen;
    //     long result = 0;

    //     for (long i = 0; i <= prefixFinish; i++) {
    //         if (isValidPrefix(i, limit)) {
    //             long power = powerOfTen * i + suffix;

    //             if (power >= start && power <= finish)
    //                 result++;
    //         }
    //     }

    //     return result;
    // }

    // private boolean isValidPrefix(long prefix, int limit) {
    //     while (prefix != 0) {
    //         long digit = prefix % 10;
    //         if (digit > limit)
    //             return false;

    //         prefix /= 10;
    //     }

    //     return true;
    // }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return count(finish, limit, s) - count(start - 1, limit, s);
    }

    private long count(long maxVal, int limit, String suffix) {
        String maxStr = Long.toString(maxVal);
        int preLen = maxStr.length() - suffix.length();

        if (preLen < 0) return 0;

        long[][] dp = new long[preLen + 1][2];
        dp[preLen][0] = 1;
        dp[preLen][1] = maxStr.substring(preLen).compareTo(suffix) >= 0 ? 1 : 0;

        for (int i = preLen - 1; i >= 0; i--) {
            int digit = maxStr.charAt(i) - '0';
            dp[i][0] = (limit + 1) * dp[i + 1][0];

            if (digit <= limit) {
                dp[i][1] = (long) digit * dp[i + 1][0] + dp[i + 1][1];
            } else {
                dp[i][1] = (long) (limit + 1) * dp[i + 1][0];
            }
        }

        return dp[0][1];
    }
}