class Solution {
    public int maxDiff(int num) {
        String str1 = String.valueOf(num); // Chuyển số sang chuỗi để thao tác thay thế
        String str2 = str1;
        int n = str1.length();

        // Tìm chữ số đầu tiên khác '9' trong str1 để thay thành '9' (tạo số lớn nhất có thể)
        int i = 0;
        while (i < n && str1.charAt(i) == '9')
            i++;

        // Nếu có chữ số khác '9', thì thay toàn bộ nó thành '9'
        char ele1 = i < n ? str1.charAt(i) : ' ';
        StringBuilder sb1 = new StringBuilder();
        for (int k = 0; k < n; k++)
            if (str1.charAt(k) == ele1)
                sb1.append('9'); // thay ele1 thành 9
            else
                sb1.append(str1.charAt(k));

        // Tạo số nhỏ nhất có thể: thay chữ số đầu tiên (thường là chữ số đầu tiên) bằng '1' hoặc các chữ số khác thành '0'
        char ele2 = str2.charAt(0); // chữ số đầu tiên
        char replace = '1';

        if (ele2 == '1')
            // Nếu chữ số đầu tiên đã là '1', tìm chữ số khác 0 và 1 để thay bằng 0
            for (int k = 1; k < n; k++) {
                char ch = str2.charAt(k);
                if (ch != '0' && ch != '1') {
                    ele2 = ch;
                    replace = '0';
                    break;
                }
            }

        StringBuilder sb2 = new StringBuilder();
        for (int k = 0; k < n; k++)
            if (str2.charAt(k) == ele2)
                sb2.append(replace); // thay ele2 thành 1 hoặc 0 tùy trường hợp
            else
                sb2.append(str2.charAt(k));

        // Trả về hiệu giữa giá trị lớn nhất và nhỏ nhất
        return Integer.parseInt(sb1.toString()) - Integer.parseInt(sb2.toString());
    }
}
