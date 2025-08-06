class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        n = len(baskets)

        # Find the smallest power of 2 >= n
        N = 1
        while N < n:
            N <<= 1

        # Create the segment tree (0-indexed, full binary tree in array form)
        segTree = [0] * (2 * N)

        # Fill the leaves (basket capacities)
        for i in range(n):
            segTree[N + i] = baskets[i]

        # Build the segment tree bottom-up
        for i in range(N - 1, 0, -1):
            segTree[i] = max(segTree[2 * i], segTree[2 * i + 1])

        count = 0  # Counter for unplaced fruits

        # Try placing each fruit
        for x in fruits:
            index = 1  # Start from the root

            # If no basket can hold the fruit
            if segTree[index] < x:
                count += 1
                continue

            # Traverse the tree to find leftmost valid basket
            while index < N:
                if segTree[2 * index] >= x:
                    index = 2 * index
                else:
                    index = 2 * index + 1

            # Mark the basket as used
            segTree[index] = -1

            # Update segment tree bottom-up
            while index > 1:
                index //= 2
                segTree[index] = max(segTree[2 * index], segTree[2 * index + 1])

        return count