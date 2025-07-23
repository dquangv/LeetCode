class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        if y > x:
            x, y = y, x
            first_pair, second_pair = "ba", "ab"
        else:
            first_pair, second_pair = "ab", "ba"

        def remove_pair(s: str, pair: str, score: int):
            stack = []
            total = 0
            for ch in s:
                if stack and stack[-1] == pair[0] and ch == pair[1]:
                    stack.pop()
                    total += score
                else:
                    stack.append(ch)
            return "".join(stack), total

        # Xử lý ưu tiên cặp có giá trị cao hơn trước
        remaining, total1 = remove_pair(s, first_pair, x)
        _, total2 = remove_pair(remaining, second_pair, y)

        return total1 + total2