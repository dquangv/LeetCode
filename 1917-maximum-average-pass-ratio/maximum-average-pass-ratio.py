class Solution:
    def maxAverageRatio(self, classes: List[List[int]], extraStudents: int) -> float:
        # Function to compute gain if we add one student
        def gain(p, t):
            return (p + 1) / (t + 1) - p / t

        # Build max-heap (Python has min-heap, so store negative gain)
        pq = [(-gain(p, t), p, t) for p, t in classes]
        heapq.heapify(pq)

        # Distribute extra students
        while extraStudents > 0:
            g, p, t = heapq.heappop(pq)  # get class with max gain
            p, t = p + 1, t + 1
            heapq.heappush(pq, (-gain(p, t), p, t))
            extraStudents -= 1

        # Compute final average ratio
        total = 0
        for _, p, t in pq:
            total += p / t
            
        return total / len(classes)
