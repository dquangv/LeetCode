class Solution {
    public int minMaxDifference(int num) {
        String s = Integer.toString(num);

        // Tìm ký tự đầu tiên khác '9' để thay thế nhằm tạo số lớn nhất
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) == '9') 
            i++;

        char toReplaceMax = s.charAt(i);
        StringBuilder maxStr = new StringBuilder();
        for (char ch : s.toCharArray())
            if (ch == toReplaceMax) 
                maxStr.append('9');
            else 
                maxStr.append(ch);

        char toReplaceMin = s.charAt(0);
        StringBuilder minStr = new StringBuilder();
        for (char ch : s.toCharArray()) 
            if (ch == toReplaceMin) 
                minStr.append('0');
            else 
                minStr.append(ch);
            
        return Integer.parseInt(maxStr.toString()) - Integer.parseInt(minStr.toString());
    }
}