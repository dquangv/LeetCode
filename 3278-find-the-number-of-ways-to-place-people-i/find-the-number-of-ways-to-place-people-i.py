class Solution:
    def numberOfPairs(self, points: List[List[int]]) -> int:
        # Step 1: Sort the points
        #   - Sort by x ASCENDING (so we consider "left to right")
        #   - If x is the same, sort by y DESCENDING
        #     (so higher y comes first; avoids counting invalid pairs later)
        points.sort(key=lambda x: (x[0], -x[1]))

        cnt = 0  # result counter

        # Step 2: Iterate each point A = (x, y)
        for i in range(len(points) - 1):
            x, y = points[i]

            # 'lower' keeps track of the lowest y we've paired so far
            # Ensures we don't "jump over" a point inside the rectangle
            lower = -1

            # Step 3: Look at all candidate B points to the right of A
            for j in range(i + 1, len(points)):
                # B must have same or greater x, and y within (lower, y]
                if lower < points[j][1] <= y:
                    # ✅ Found a valid pair (A, B)
                    cnt += 1

                    # Update lower bound (block anything above current B)
                    lower = points[j][1]

                # If we've already matched the lowest possible (== y),
                # no need to keep searching further
                if lower == y:
                    break

        # Step 4: Return final count
        return cnt
