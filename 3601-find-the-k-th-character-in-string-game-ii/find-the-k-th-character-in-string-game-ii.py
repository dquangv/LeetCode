class Solution:
    def kthCharacter(self, k: int, operations: List[int]) -> str:
        shifts = 0

        while k != 1:
            # Tìm vị trí bit cao nhất (i sao cho 2^i <= k)
            i = k.bit_length() - 1

            # Nếu k là số lũy thừa đúng (2^i), giảm i đi 1
            if (1 << i) == k:
                i -= 1

            k -= (1 << i)

            # Nếu phép biến đổi tại bước i là loại 1, tăng shift
            if operations[i] == 1:
                shifts += 1

        # Trả về ký tự sau khi đã shift từ 'a'
        return chr((shifts % 26) + ord('a'))