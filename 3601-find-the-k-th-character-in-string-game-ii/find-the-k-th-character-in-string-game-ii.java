class Solution {
    public char kthCharacter(long k, int[] operations) {
        long shifts = 0; // Số lần "shift chữ cái" do operation[i] == 1 (tức là biến đổi thành next character)

        while (k != 1) {
            // Tìm bit cao nhất (i sao cho 2^i <= k)
            int i = 63 - Long.numberOfLeadingZeros(k);

            // Nếu k là đúng lũy thừa của 2 (ví dụ: k = 4 → 100), ta phải giảm i đi 1
            // vì phần tử đầu tiên (gốc) là tại k = 1 → các đoạn tiếp theo bắt đầu từ 2^0 = 1
            if ((1L << i) == k) i--;

            // Giảm k về vị trí tương ứng ở nửa đầu trước đó
            k -= (1L << i);

            // Nếu phép biến đổi tại bước i là loại 1 (biến đổi chữ cái),
            // thì cộng thêm 1 lần shift
            if (operations[i] == 1) shifts++;
        }

        // Sau khi k đã trở về vị trí 1 (ban đầu là 'a'),
        // áp dụng tất cả các phép shift (số lần operation[i] == 1 dọc theo đường đi)
        return (char) ((shifts % 26) + 97); // 97 = mã ASCII của 'a'
    }
}
