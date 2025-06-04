class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        # Nếu chỉ có 1 người bạn thì không cần chia, trả về nguyên chuỗi
        if numFriends == 1:
            return word

        n = len(word)
        res = ""

        for i in range(n):
            # Tính chiều dài tối đa của chuỗi con tại vị trí i
            # - (len - numFriends + 1): để đảm bảo chia được thành numFriends chuỗi không rỗng
            # - (n - i): để không vượt quá chuỗi gốc
            sub_len = min(n - numFriends + 1, n - i)
            sub = word[i:i + sub_len]

            # So sánh theo thứ tự từ điển để lấy chuỗi lớn nhất
            if sub > res:
                res = sub

        return res