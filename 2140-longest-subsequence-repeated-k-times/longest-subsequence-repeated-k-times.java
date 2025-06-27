class Solution {
    int n, maxLen;
    String ans = "";
    List<Character> chars = new ArrayList<>();

    // Hàm kiểm tra liệu chuỗi t lặp lại k lần có phải subsequence của s không
    private boolean repeatK(String s, String t, int m, int k) {
        int j = 0;
        for (int i = 0; i < s.length() && k > 0; i++)
            if (s.charAt(i) == t.charAt(j)) {
                j++;
                if (j == m) {
                    k--;
                    j = 0; // reset pointer cho subsequence tiếp theo
                }
            }

        return k == 0;
    }

    // Hàm DFS để tạo và thử tất cả subsequences theo chiều giảm dần lexicographically
    private void dfs(String s, StringBuilder t, int k) {
        int m = t.length();

        // Nếu dài hơn maxLen cho phép thì dừng lại
        if (m > maxLen)
            return;

        // Nếu t hiện tại không lặp được k lần thì dừng nhánh này
        if (m > 0 && !repeatK(s, t.toString(), m, k))
            return;

        // Nếu t là subsequence hợp lệ và dài hơn kết quả hiện tại -> cập nhật
        if (m > ans.length())
            ans = t.toString();

        // Tiếp tục đệ quy thêm các ký tự có thể
        for (char c : chars) {
            t.append(c);
            dfs(s, t, k);
            t.deleteCharAt(t.length() - 1); // backtrack
        }
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int[] freq = new int[26];
        BitSet hasX = new BitSet(26);

        // Đếm tần suất từng ký tự
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            freq[idx]++;
            if (freq[idx] == k)
                hasX.set(idx);
        }

        if (hasX.isEmpty())
            return ""; // Không có ký tự nào đủ điều kiện xuất hiện >= k lần

        // Rút gọn chuỗi s: chỉ giữ lại các ký tự có thể tạo subsequence
        StringBuilder filtered = new StringBuilder();
        for (char c : s.toCharArray())
            if (hasX.get(c - 'a'))
                filtered.append(c);

        s = filtered.toString();
        n = s.length();
        maxLen = n / k; // Độ dài subsequence tối đa

        // Thêm ký tự vào danh sách, duyệt từ z đến a để đảm bảo kết quả lớn nhất
        for (int i = 25; i >= 0; i--)
            if (hasX.get(i))
                chars.add((char) ('a' + i));

        dfs(s, new StringBuilder(), k); // Bắt đầu đệ quy

        return ans;
    }
}
