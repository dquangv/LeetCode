class Solution(object):
    def maxCandies(self, status, candies, keys, containedBoxes, initialBoxes):
        """
        :type status: List[int]
        :type candies: List[int]
        :type keys: List[List[int]]
        :type containedBoxes: List[List[int]]
        :type initialBoxes: List[int]
        :rtype: int
        """
        n = len(status)
        has_key = [False] * n         # Track if we have the key to a box
        has_box = [False] * n         # Track if we physically have a box
        opened = [False] * n          # Track if a box has already been opened
        queue = deque()               # Use deque for efficient queue operations
        total = 0                     # Total candies collected

        # Initialize: mark the initial boxes we have
        for box in initialBoxes:
            has_box[box] = True
            if status[box] == 1:
                queue.append(box)
                opened[box] = True

        while queue:
            curr = queue.popleft()

            # Collect candies in the box
            total += candies[curr]

            # Collect keys from this box
            for key in keys[curr]:
                has_key[key] = True
                # If we already have the box and it's not opened, now we can open it
                if has_box[key] and not opened[key]:
                    queue.append(key)
                    opened[key] = True

            # Collect boxes inside this box
            for box in containedBoxes[curr]:
                has_box[box] = True
                # If we can open this box (either it's open or we have the key)
                if status[box] == 1 or has_key[box]:
                    if not opened[box]:
                        queue.append(box)
                        opened[box] = True

        return total