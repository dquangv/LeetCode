class Solution:
    def numberOfPairs(self, P: List[List[int]]) -> int:
        # Step 1: Sort the points
        #   - Sort by x in DESCENDING order (so Alice is always "left" of Bob)
        #   - If x is the same, sort by y ASCENDING (smaller y first)
        P.sort(key=lambda p: (-p[0], p[1]))

        ans, n = 0, len(P)

        # Step 2: Iterate over all possible Alice positions
        for i in range(n - 1):
            yi = P[i][1]  # Alice's y-coordinate
            y = 1 << 31  # very large (acts as initial upper bound)

            # Step 3: Iterate over possible Bob positions (must be to the "right")
            for j in range(i + 1, n):
                yj = P[j][1]  # Bob's y-coordinate

                # Valid condition:
                # Bob must be within vertical strip (yj >= yi)
                # and must be strictly below the last chosen "y"
                if y > yj >= yi:
                    ans += 1  # ✅ Found valid Alice-Bob pair
                    y = yj  # shrink the vertical window

                    # Optimization: if Bob is on the same horizontal line as Alice,
                    # we can't go further down (rectangle becomes a line)
                    if yi == yj:
                        break
        return ans