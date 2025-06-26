class Solution {
    public int longestSubsequence(String s, int k) {
        int sum = 0;
        int length = 0;
        // Số lượng bit tối đa có thể dùng để không vượt quá k
        int maxBits = (int) (Math.log(k) / Math.log(2)) + 1;

        // Duyệt từ phải sang trái (bit có trọng số thấp nhất trước)
        for (int i = 0; i < s.length(); i++) {
            char bit = s.charAt(s.length() - 1 - i);

            if (bit == '1') {
                // Nếu bit là '1', chỉ thêm nếu tổng không vượt k và không quá maxBits
                if (i < maxBits && sum + (1 << i) <= k) {
                    sum += 1 << i; // cộng giá trị tương ứng với bit '1'
                    length++;
                }
            } else
                // Nếu bit là '0', luôn có thể thêm mà không tăng giá trị nhị phân
                length++;
        }

        return length;
    }
}
