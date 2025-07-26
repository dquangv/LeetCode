class Solution:
    def maxSubarrays(self, n: int, conflictingPairs: List[List[int]]) -> int:
        conflicts = [[] for _ in range(n + 1)]

        # Build conflict map: conflicts[i] = all j < i that conflict with i
        for a, b in conflictingPairs:
            left, right = min(a, b), max(a, b)
            conflicts[right].append(left)

        restrict_removal = [0] * (n + 1)
        left_max_restrict = 0
        left_second_max_restrict = 0
        res = 0

        for i in range(1, n + 1):
            for ele in conflicts[i]:
                if ele > left_max_restrict:
                    left_second_max_restrict = left_max_restrict
                    left_max_restrict = ele
                elif ele > left_second_max_restrict:
                    left_second_max_restrict = ele

            res += i - left_max_restrict
            restrict_removal[left_max_restrict] += left_max_restrict - left_second_max_restrict

        max_removal_val = max(restrict_removal)
        return res + max_removal_val