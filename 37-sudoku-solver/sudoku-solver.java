class Solution {
    public void solveSudoku(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] blocks = new HashSet[9];
        List<int[]> emptyCells = new ArrayList<>();

        // Initialize sets
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            blocks[i] = new HashSet<>();
        }

        // Pre-fill sets with existing numbers
        for (int r = 0; r < 9; r++) 
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') 
                    emptyCells.add(new int[] { r, c });
                 else {
                    char val = board[r][c];
                    rows[r].add(val);
                    cols[c].add(val);
                    blocks[(r / 3) * 3 + (c / 3)].add(val);
                }
            }

        backtrack(board, rows, cols, blocks, emptyCells, 0);
    }

    private boolean backtrack(char[][] board, Set<Character>[] rows, Set<Character>[] cols,
            Set<Character>[] blocks, List<int[]> emptyCells, int k) {
        if (k == emptyCells.size())
            return true;

        int r = emptyCells.get(k)[0];
        int c = emptyCells.get(k)[1];
        int b = (r / 3) * 3 + (c / 3);

        for (char ch = '1'; ch <= '9'; ch++)
            if (!rows[r].contains(ch) && !cols[c].contains(ch) && !blocks[b].contains(ch)) {
                board[r][c] = ch;
                rows[r].add(ch);
                cols[c].add(ch);
                blocks[b].add(ch);

                if (backtrack(board, rows, cols, blocks, emptyCells, k + 1))
                    return true;

                // Backtrack (undo choice)
                board[r][c] = '.';
                rows[r].remove(ch);
                cols[c].remove(ch);
                blocks[b].remove(ch);
            }

        return false;
    }
}