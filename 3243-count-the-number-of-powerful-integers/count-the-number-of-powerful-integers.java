class Solution {
    // Time Limit Exceeded (start = 123456, finish = 32486458654, limit = 4, s = "1")

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long suffix = Long.parseLong(s);

        if (finish < suffix)
            return 0;

        int lengthSuf = s.length();
        long powerOfTen = (long) Math.pow(10, lengthSuf);

        long prefixStart = start / powerOfTen;
        long prefixFinish = finish / powerOfTen;

        // long result = 0;

        // for (long i = 0; i <= prefixFinish; i++) {
        //     if (isValidPrefix(i, limit)) {
        //         long power = powerOfTen * i + suffix;

        //         if (power > finish) break;

        //         if (power >= start && power <= finish)
        //             result++;
        //     }
        // }

        // return result;

        if (start % powerOfTen > suffix)
            prefixStart++;
        if (finish % powerOfTen >= suffix)
            prefixFinish++;

        return countValidPrefixes(prefixFinish, limit) - countValidPrefixes(prefixStart, limit);
    }

    // private boolean isValidPrefix(long prefix, int limit) {
    //     while (prefix != 0) {
    //         long digit = prefix % 10;
    //         if (digit > limit)
    //             return false;

    //         prefix /= 10;
    //     }

    //     return true;
    // }

    private long countValidPrefixes(long num, int limit) {
        if (num == 0)
            return 0;
        if (limit == 9)
            return num; // tất cả số đều hợp lệ

        int numDigits = (int) Math.log10(num); // số chữ số - 1
        long divisor = (long) Math.pow(10, numDigits);
        long result = 0;

        for (int i = numDigits; i >= 0; i--) {
            int digit = (int) (num / divisor);

            if (digit > limit)
                return result + (long) Math.pow(limit + 1, i + 1); // tất cả tổ hợp còn lại hợp lệ

            result += digit * (long) Math.pow(limit + 1, i); // cộng số tổ hợp nhỏ hơn digit tại vị trí này

            num %= divisor;
            divisor /= 10;
        }

        return result;
    }
}