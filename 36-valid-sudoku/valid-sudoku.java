class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Create 9 sets for rows, columns, and blocks
        Set<Character>[] Row = new HashSet[9];
        Set<Character>[] Col = new HashSet[9];
        Set<Character>[] Block = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            Row[i] = new HashSet<>();
            Col[i] = new HashSet<>();
            Block[i] = new HashSet<>();
        }

        // Traverse the board
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;

                // ✅ Check row
                if (Row[i].contains(c)) return false;
                Row[i].add(c);

                // ✅ Check column
                if (Col[j].contains(c)) return false;
                Col[j].add(c);

                // ✅ Check block
                int idx = (i / 3) * 3 + (j / 3);
                if (Block[idx].contains(c)) return false;
                Block[idx].add(c);
            }

        return true;
    }
}
