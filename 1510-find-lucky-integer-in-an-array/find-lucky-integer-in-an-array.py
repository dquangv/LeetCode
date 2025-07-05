class Solution:
    def findLucky(self, arr: List[int]) -> int:
        freq = [0] * 501
        max_val = 0
        result = -1

        for num in arr:
            freq[num] += 1
            max_val = max(max_val, num)

        for i in range(max_val, 0, -1):
            if freq[i] == i:
                result = i
                break

        return result