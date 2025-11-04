class Solution:
    def findXSum(self, nums: List[int], k: int, x: int) -> List[int]:
        def xsum(subarray, x):
            count = Counter(subarray)
            heap = [(-freq, -num) for num, freq in count.items()]
            heapq.heapify(heap)
            ans = 0
            i = 0
            while heap and i < x:
                freq, num = heapq.heappop(heap)
                ans += num * freq
                i += 1
            return ans

        i = 0
        result = []
        while i + k < len(nums) + 1:
            subarray = nums[i : i + k]
            result.append(xsum(subarray, x))
            i += 1
        return result
