class Solution {

    // Tạo số đối xứng (palindrome) trong base-10 từ một nửa số `num`
    // Nếu `odd` là true → tạo số có độ dài lẻ (bỏ chữ số giữa)
    long createPalindrome(long num, boolean odd) {
        long x = num;
        if (odd)
            x /= 10;
        while (x > 0) {
            num = num * 10 + x % 10;
            x /= 10;
        }
        return num;
    }

    // Kiểm tra xem số `num` có là palindrome trong base-k hay không
    boolean isPalindrome(long num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append((char) (num % base + '0'));
            num /= base;
        }
        String s = sb.toString();
        int i = 0, j = s.length() - 1;
        while (i < j)
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        return true;
    }

    // Hàm chính để tính tổng n số k-mirror nhỏ nhất
    public long kMirror(int k, int n) {
        long sum = 0;
        // len dùng để tạo số đối xứng từ các nửa số có độ dài tăng dần
        for (long len = 1; n > 0; len *= 10) {
            // Tạo số palindrome có độ dài lẻ
            for (long i = len; n > 0 && i < len * 10; i++) {
                long p = createPalindrome(i, true);
                if (isPalindrome(p, k)) {
                    sum += p;
                    n--;
                }
            }
            // Tạo số palindrome có độ dài chẵn
            for (long i = len; n > 0 && i < len * 10; i++) {
                long p = createPalindrome(i, false);
                if (isPalindrome(p, k)) {
                    sum += p;
                    n--;
                }
            }
        }
        return sum;
    }
}
