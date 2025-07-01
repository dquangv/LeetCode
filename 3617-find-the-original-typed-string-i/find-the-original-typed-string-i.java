class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();
        int count = n;

        for (int i = 1; i < n; i++)
            // Nếu ký tự hiện tại khác ký tự trước đó => không phải trùng (không thể rút gọn thêm)
            if (word.charAt(i) != word.charAt(i - 1))
                count--; // Giảm số lượng vị trí có thể xảy ra "press too long"
            
        

        // Kết quả là số lượng vị trí có thể rút gọn đi 1 ký tự trong 1 chuỗi lặp,
        // tức là mỗi cụm lặp có thể giảm đi đúng 1 ký tự (mô phỏng hành động gõ dư 1 lần)
        return count;
    }
}