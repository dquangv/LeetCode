class Solution:
    def areaOfMaxDiagonal(self, dimensions: List[List[int]]) -> int:
        # Use `max` with a custom key function to find the rectangle
        # The key is a tuple:
        #   (1) First: maximize diagonal length squared = length^2 + width^2
        #   (2) If diagonals are equal, maximize area = length * width
        it = max(dimensions, key=lambda r: (r[0] ** 2 + r[1] ** 2, r[0] * r[1]))

        # Return the area (length * width) of the chosen rectangle
        return it[0] * it[1]
