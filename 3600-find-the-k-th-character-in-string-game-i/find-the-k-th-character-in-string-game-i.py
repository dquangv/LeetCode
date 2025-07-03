class Solution:
    def kthCharacter(self, k: int) -> str:
        # Trả về ký tự thứ k trong chuỗi sinh ra, dựa trên số bit 1 trong (k-1)
        return chr(ord('a') + bin(k - 1).count('1'))