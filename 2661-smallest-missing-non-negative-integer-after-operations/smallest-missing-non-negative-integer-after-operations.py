class Solution:
    def findSmallestInteger(self, nums: List[int], value: int) -> int:
        counter = Counter([x % value for x in nums])
        ans = 0
        while counter[ans % value] > 0:
            counter[ans % value] -= 1
            ans += 1

        return ans