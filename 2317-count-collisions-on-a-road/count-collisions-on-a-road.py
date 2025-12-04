class Solution:
    def countCollisions(self, directions: str) -> int:
        has_stationary, right, collisions = False, 0, 0
        for direction in directions:
            if direction == "R":
                right += 1
            elif direction == "L" and (has_stationary or right > 0):
                collisions += 1 + right
                right = 0
                has_stationary = True
            elif direction == "S":
                collisions += right
                right = 0
                has_stationary = True
        return collisions
