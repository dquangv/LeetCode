class DSU:
    def __init__(self, n):
        self.pile = list(range(n + 1))

    def find(self, x):
        while self.pile[x] != x:
            self.pile[x] = self.pile[self.pile[x]]
            x = self.pile[x]
        return x

    def union(self, a, b):
        ra, rb = self.find(a), self.find(b)
        if ra != rb:
            self.pile[rb] = ra


class Solution:
    def processQueries(
        self, c: int, connections: list[list[int]], queries: list[list[int]]
    ) -> list[int]:
        dsu = DSU(c)
        for u, v in connections:
            dsu.union(u, v)
        comp = collections.defaultdict(list)
        for i in range(1, c + 1):
            comp[dsu.find(i)].append(i)
        heaps = {}
        for root, lst in comp.items():
            heap = lst[:]
            heapq.heapify(heap)
            heaps[root] = heap
        online = [True] * (c + 1)
        answer = []
        for t, x in queries:
            if t == 2:
                online[x] = False
            else:
                if online[x]:
                    answer.append(x)
                else:
                    h = heaps[dsu.find(x)]
                    while h and not online[h[0]]:
                        heapq.heappop(h)
                    answer.append(h[0] if h else -1)
        return answer
