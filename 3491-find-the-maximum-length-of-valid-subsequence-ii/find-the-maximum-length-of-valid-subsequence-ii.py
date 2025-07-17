class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if k == 1:
            return n

        res = 2
        mod_arr = [num % k for num in nums]  # Tính phần dư của mỗi phần tử theo k

        for j in range(k):  # Xét từng giá trị mục tiêu của tổng 2 phần tử liên tiếp modulo k
            dp = [0] * k  # dp[i] là độ dài subsequence dài nhất kết thúc với phần dư i
            for mod in mod_arr:
                pos = (j - mod + k) % k  # Tìm phần tử trước có thể ghép với mod để tổng = j mod k
                dp[mod] = dp[pos] + 1  # Cập nhật dp cho phần tử hiện tại
                res = max(res, dp[mod])  # Cập nhật kết quả

        return res
