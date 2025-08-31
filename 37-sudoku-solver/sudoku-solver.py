class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        # Track used numbers in each row, column, and 3x3 block
        rows = [set() for _ in range(9)]
        cols = [set() for _ in range(9)]
        blocks = [set() for _ in range(9)]
        emptyCells = []  # list of positions of empty cells (row, col)

        # Pre-fill sets with existing numbers on the board
        for r in range(9):
            for c in range(9):
                if board[r][c] == ".":
                    emptyCells.append((r, c))  # record empty position
                else:
                    val = board[r][c]
                    rows[r].add(val)
                    cols[c].add(val)
                    blocks[(r // 3) * 3 + (c // 3)].add(val)

        # Backtracking function
        def backtrack(k):
            # If all empty cells are filled → solved
            if k == len(emptyCells):
                return True

            r, c = emptyCells[k]
            b = (r // 3) * 3 + (c // 3)  # block index (0–8)

            for ch in "123456789":
                # Try placing a digit if it's not used in row/col/block
                if ch not in rows[r] and ch not in cols[c] and ch not in blocks[b]:
                    board[r][c] = ch
                    rows[r].add(ch)
                    cols[c].add(ch)
                    blocks[b].add(ch)

                    # Recurse to next empty cell
                    if backtrack(k + 1):
                        return True

                    # Undo choice (backtrack)
                    board[r][c] = "."
                    rows[r].remove(ch)
                    cols[c].remove(ch)
                    blocks[b].remove(ch)

            return False

        backtrack(0)