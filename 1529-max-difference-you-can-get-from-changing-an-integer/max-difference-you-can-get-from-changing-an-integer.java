class Solution {
    private int difference(int num) {
        // Các biến để xác định chữ số đầu tiên không phải 9, không phải 1, và chữ số đầu tiên
        int firstNonNine = -1, firstNonOne = -1, firstDigit = -1;
        int remaining = num;

        // Lặp qua các chữ số của num (từ phải sang trái)
        while (remaining > 0) {
            int digit = remaining % 10;

            // Tìm chữ số đầu tiên khác 9 (dùng để tạo max)
            if (digit != 9)
                firstNonNine = digit;

            // Tìm chữ số đầu tiên > 1 (dùng để tạo min khi chữ số đầu tiên là 1)
            if (digit > 1)
                firstNonOne = digit;

            // Cập nhật chữ số đầu tiên (bên trái nhất)
            firstDigit = digit;

            remaining /= 10;
        }

        // Reset lại num để xử lý
        remaining = num;
        int min = 0, max = 0;
        int multiplier = 1;

        // Duyệt lại từng chữ số từ phải sang trái để tạo min và max
        while (remaining > 0) {
            int digit = remaining % 10;
            int minDigit = digit;
            int maxDigit = digit;

            // Logic tạo min:
            // Nếu chữ số đầu tiên là 1 → thay firstNonOne bằng 0
            if (firstDigit == 1 && digit == firstNonOne)
                minDigit = 0;

            // Nếu chữ số đầu tiên không phải 1 → thay nó bằng 1
            if (firstDigit != 1 && digit == firstDigit)
                minDigit = 1;

            // Logic tạo max: thay firstNonNine thành 9
            if (digit == firstNonNine)
                maxDigit = 9;

            // Cộng dồn vào min và max theo từng hàng
            min += multiplier * minDigit;
            max += multiplier * maxDigit;

            multiplier *= 10;
            remaining /= 10;
        }

        return max - min;
    }

    public int maxDiff(int num) {
        return difference(num);
    }
}
