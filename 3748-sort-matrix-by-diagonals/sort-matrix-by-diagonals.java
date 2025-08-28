class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagonals = new HashMap<>();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int key = i - j;
                diagonals.putIfAbsent(key, new ArrayList<>());
                diagonals.get(key).add(grid[i][j]);
            }

        for (int key : diagonals.keySet()) {
            List<Integer> diagonal = diagonals.get(key);

            if (key >= 0)
                diagonal.sort(Collections.reverseOrder());
            else
                Collections.sort(diagonal);
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = diagonals.get(i - j).remove(0);

        return grid;
    }
}