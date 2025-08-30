class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # Create 9 sets for rows, 9 for columns, and 9 for 3x3 sub-boxes
        Col = [set() for _ in range(9)]
        Row = [set() for _ in range(9)]
        Block = [set() for _ in range(9)]

        for i in range(9):  # row index
            for j in range(9):  # column index
                c = board[i][j]
                if c == ".":
                    continue  # skip empty cells

                # Convert character '1'-'9' to int 1-9
                x = (ord(c) - ord("0")) % 9

                # ✅ Check row constraint
                if x in Row[i]:
                    return False  # duplicate found in same row
                Row[i].add(x)

                # ✅ Check column constraint
                if x in Col[j]:
                    return False  # duplicate found in same column
                Col[j].add(x)

                # ✅ Check sub-box constraint
                idx = (i // 3) * 3 + (j // 3)  # sub-box index (0–8)
                if x in Block[idx]:
                    return False  # duplicate found in same 3x3 box
                Block[idx].add(x)

        return True