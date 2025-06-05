class Solution:
    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        # Mỗi ký tự từ 'a' đến 'z' sẽ được đại diện bởi chính nó ban đầu
        parent = list(range(26))  # 26 chữ cái tiếng Anh

        # Hàm tìm gốc đại diện của 1 ký tự, có áp dụng nén đường đi (path compression)
        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])  # Gán lại cha trực tiếp để tối ưu
            return parent[x]

        # Hàm hợp nhất 2 nhóm tương đương
        def union(x, y):
            px = find(x)
            py = find(y)
            # Luôn lấy ký tự nhỏ hơn làm đại diện để đảm bảo chuỗi kết quả là nhỏ nhất về từ điển
            if px < py:
                parent[py] = px
            else:
                parent[px] = py

        # Duyệt từng cặp ký tự tương đương từ s1 và s2
        for a, b in zip(s1, s2):
            union(ord(a) - ord('a'), ord(b) - ord('a'))

        # Xây dựng kết quả: với mỗi ký tự trong baseStr, thay bằng đại diện nhỏ nhất tương đương
        result = ""
        for ch in baseStr:
            result += chr(find(ord(ch) - ord('a')) + ord('a'))

        return result
