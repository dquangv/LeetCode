class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        # Total number of pairs (x, y) = n * m
        # But only half of them lead to Alice winning,
        # since Alice wins when (x + y) is odd.
        # Because in any rectangular grid of numbers,
        # about half of the sums are odd and half are even.
        
        return n * m // 2