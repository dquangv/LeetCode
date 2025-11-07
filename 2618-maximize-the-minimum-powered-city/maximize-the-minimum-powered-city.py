class Solution:
    def maxPower(self, stations: List[int], r: int, k: int) -> int:
        n = len(stations)
        left, right = 0, k + sum(stations)
        while left <= right:
            x = (left + right) // 2
            use = 0
            v = stations.copy()
            s = sum(stations[0:r])
            for i in range(n):
                t = n - 1 if n - 1 < i + r else i + r
                if i + r < n:
                    s += v[i + r]
                if i - r > 0:
                    s -= v[i - r - 1]
                diff = x - s if x - s > 0 else 0
                v[t] += diff
                s += diff
                use += diff
            if use <= k:
                left = x + 1
            else:
                right = x - 1
        return right
