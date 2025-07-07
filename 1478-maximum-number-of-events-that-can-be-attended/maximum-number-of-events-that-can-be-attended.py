class Solution:
    def maxEvents(self, events: list[list[int]]) -> int:
        events.sort(key=lambda x: x[1])  # Sort by end day

        max_day = max(e[1] for e in events)
        parent = list(range(max_day + 2))  # Union-find parent array

        # Find the next available day >= d with path compression
        def find(d):
            if parent[d] != d:
                parent[d] = find(parent[d])
            return parent[d]

        count = 0
        for start, end in events:
            day = find(start)  # Find earliest free day to attend this event
            if day <= end:
                count += 1
                parent[day] = find(day + 1)  # Mark day as used

        return count
