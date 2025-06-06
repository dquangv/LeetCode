class Solution {
    // Trả về ký tự nhỏ nhất còn lại dựa trên tần suất các ký tự
    private char smallestRemainingChar(int[] freq) {
        for (int i = 0; i < 26; i++) 
            if (freq[i] > 0) 
                return (char) ('a' + i);
        
        return 'a'; // default fallback
    }

    public String robotWithString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int[] freq = new int[26]; // Đếm số lần xuất hiện mỗi chữ cái trong s

        // Đếm tần suất từng ký tự trong chuỗi s
        for (char ch : s.toCharArray())
            freq[ch - 'a']++;
        
        // Duyệt từng ký tự trong s
        for (char ch : s.toCharArray()) {
            // Đưa ký tự vào stack tạm t (do robot đang giữ t)
            stack.push(ch);
            freq[ch - 'a']--; // Giảm số lượng ký tự còn lại

            // Nếu ký tự trên đầu stack nhỏ hơn hoặc bằng ký tự nhỏ nhất còn lại trong s
            // thì đưa ra khỏi stack và ghi vào giấy (append vào kết quả)
            while (!stack.isEmpty() && stack.peek() <= smallestRemainingChar(freq)) 
                result.append(stack.pop());
        }

        // Nếu còn lại ký tự nào trong stack thì pop hết ra (tương đương ghi hết t còn lại)
        while (!stack.isEmpty()) 
            result.append(stack.pop());
        
        return result.toString(); // Trả về kết quả cuối cùng
    }
}