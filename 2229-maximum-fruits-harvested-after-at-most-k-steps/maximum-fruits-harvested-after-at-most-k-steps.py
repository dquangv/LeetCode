class Solution:
    def maxTotalFruits(self, fruits: list[list[int]], startPos: int, k: int) -> int:
        res = 0
        left = 0
        curr_sum = 0

        for right in range(len(fruits)):
            curr_sum += fruits[right][1]

            # shrink window from the left if invalid
            while left <= right and not self.isValidRange(fruits[left][0], fruits[right][0], startPos, k):
                curr_sum -= fruits[left][1]
                left += 1

            res = max(res, curr_sum)

        return res

    def isValidRange(self, leftPos: int, rightPos: int, startPos: int, k: int) -> bool:
        if rightPos <= startPos:
            return startPos - leftPos <= k
        elif leftPos >= startPos:
            return rightPos - startPos <= k
        else:
            left = startPos - leftPos
            right = rightPos - startPos
            return (left * 2 + right <= k) if left <= right else (right * 2 + left <= k)
