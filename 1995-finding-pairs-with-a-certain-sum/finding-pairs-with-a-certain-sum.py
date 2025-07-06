class FindSumPairs:

    def __init__(self, nums1: List[int], nums2: List[int]):
        self.nums1 = sorted(nums1)
        self.nums2 = nums2
        self.freq = Counter(nums2)
        self.xMax = max(nums2)

    def add(self, index: int, val: int) -> None:
        old_val = self.nums2[index]
        self.freq[old_val] -= 1
        if self.freq[old_val] == 0:
            del self.freq[old_val]

        self.nums2[index] += val
        new_val = self.nums2[index]
        self.freq[new_val] += 1
        self.xMax = max(self.xMax, new_val)

    def count(self, tot: int) -> int:
        threshold = max(1, tot - self.xMax)
        i0 = bisect_left(self.nums1, threshold)

        count = 0
        for i in range(i0, len(self.nums1)):
            x = self.nums1[i]
            if x >= tot:
                break
            count += self.freq.get(tot - x, 0)
        return count


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)