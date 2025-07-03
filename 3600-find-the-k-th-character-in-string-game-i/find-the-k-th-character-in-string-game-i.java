class Solution {
    public char kthCharacter(int k) {
        // Bản chất của bài toán: đếm số bit 1 trong (k-1)
        // Sau mỗi lần nối chuỗi, chữ cái được chuyển sang ký tự kế tiếp trong bảng chữ cái
        // Vị trí k sẽ có ký tự = 'a' + số lần ký tự được "tăng" -> tương đương với số bit 1 trong k-1
        return (char) ('a' + Integer.bitCount(k - 1));
    }
}