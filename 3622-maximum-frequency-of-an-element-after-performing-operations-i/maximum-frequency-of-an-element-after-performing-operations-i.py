class Solution:
    def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
        mx = max(nums) + 1
        ans = prev = 0

        ctr = [0] * mx                    
        for num in nums: ctr[num]+= 1
        curr = sum(ctr[:k])
       
        for target in range(mx):

            curr-= ctr[target]                         
     
            if target < mx - k : curr+= ctr[target + k]
            if target > 0      : prev+= ctr[target - 1]
            if target > k + 1  : prev-= ctr[target - (k + 1)]

            increment = min(numOperations, curr + prev)

            ans = max(ans, ctr[target] + increment)  

        return ans