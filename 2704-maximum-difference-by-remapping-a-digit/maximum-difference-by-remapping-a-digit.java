class Solution {
    private int difference(int num) {
        int firstNonNine = -1, firstDigit = -1;
        int remaining = num;

        // Vòng lặp tìm chữ số đầu tiên khác 9 và chữ số đầu tiên của số
        while (remaining > 0) {
            int digit = remaining % 10;

            // Ghi lại chữ số đầu tiên khác 9 (dùng để tạo số max)
            if (digit != 9) 
                firstNonNine = digit;
            
            // Ghi lại chữ số đầu tiên từ phải sang (dùng để tạo số min)
            firstDigit = digit;

            remaining /= 10;
        }

        // Reset lại để bắt đầu tạo số mới
        remaining = num;
        int min = 0, max = 0;
        int multiplier = 1;

        // Duyệt từng chữ số để tạo số min và max
        while (remaining > 0) {
            int digit = remaining % 10;

            int minDigit = digit;
            int maxDigit = digit;

            // Nếu digit là chữ số đầu tiên → thay bằng 0 để tạo số nhỏ nhất
            if (digit == firstDigit) 
                minDigit = 0;

            // Nếu digit là chữ số đầu tiên khác 9 → thay bằng 9 để tạo số lớn nhất
            if (digit == firstNonNine) 
                maxDigit = 9;

            // Cộng giá trị vào kết quả
            min += multiplier * minDigit;
            max += multiplier * maxDigit;

            multiplier *= 10;
            remaining /= 10;
        }

        return max - min;
    }

    public int minMaxDifference(int num) {
        return difference(num);
    }
}
