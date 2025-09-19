class Spreadsheet {
    private int rows;
    private Map<String, Integer> cells;

    public Spreadsheet(int rows) {
        // Initialize spreadsheet with given rows
        this.rows = rows;
        this.cells = new HashMap<>();
    }

    public void setCell(String cell, int value) {
        // Set/update the value of the given cell
        cells.put(cell, value);
    }

    public void resetCell(String cell) {
        // Reset the cell to 0
        cells.put(cell, 0);
    }

    // Helper method to evaluate either a number or a cell reference
    private int val(String x) {
        if (Character.isLetter(x.charAt(0))) {
            // It's a cell reference
            return cells.getOrDefault(x, 0);
        } else {
            // It's a plain number
            return Integer.parseInt(x);
        }
    }

    public int getValue(String formula) {
        // Formula is of the form "=X+Y"
        String expr = formula.substring(1); // remove "="
        String[] parts = expr.split("\\+"); // split at "+"
        return val(parts[0]) + val(parts[1]);
    }
}

/**
 * Usage:
 * Spreadsheet obj = new Spreadsheet(10);
 * obj.setCell("A1", 5);
 * obj.setCell("B1", 3);
 * System.out.println(obj.getValue("=A1+B1")); // 8
 */
