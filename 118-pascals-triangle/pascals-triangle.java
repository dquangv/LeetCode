class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0)
            return new ArrayList<>();

        if (numRows == 1) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(Arrays.asList(1));
            return result;
        }

        List<List<Integer>> prevRows = generate(numRows - 1);
        List<Integer> newRow = new ArrayList<>();

        newRow.add(1);

        for (int i = 1; i < numRows - 1; i++)
            newRow.add(i, prevRows.get(numRows - 2).get(i - 1) + prevRows.get(numRows - 2).get(i));

        newRow.add(1);

        prevRows.add(newRow);
        return prevRows;
    }
}