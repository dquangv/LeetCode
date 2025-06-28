class Solution:
    def maxSubsequence(self, nums: List[int], k: int) -> List[int]:
        # Tạo mảng gồm (giá trị, chỉ số)
        indexed = [(val, idx) for idx, val in enumerate(nums)]

        # Lấy k phần tử có giá trị lớn nhất (ưu tiên giá trị cao hơn)
        largest_k = heapq.nlargest(k, indexed, key=lambda x: (x[0], -x[1]))

        # Sắp xếp theo thứ tự chỉ số để giữ đúng subsequence ban đầu
        largest_k.sort(key=lambda x: x[1])

        # Trả về giá trị
        return [val for val, idx in largest_k]