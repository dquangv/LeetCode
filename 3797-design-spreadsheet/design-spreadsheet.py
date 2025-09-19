class Spreadsheet:

    def __init__(self, rows: int):
        # Number of rows in the spreadsheet (not really used directly,
        # but could help validate input if needed)
        self.rows = rows

        # Dictionary to store cell values
        # Key = cell reference (e.g., "A1"), Value = integer
        self.cells = {}

    def setCell(self, cell: str, value: int) -> None:
        # Store/update the given cell with the specified value
        self.cells[cell] = value

    def resetCell(self, cell: str) -> None:
        # Reset the cell’s value to 0
        self.cells[cell] = 0

    def val(self, x):
        # Helper function to evaluate a token (either a cell reference or an integer)
        if x[0].isalpha():  # If the token starts with a letter, it's a cell reference
            return self.cells.get(x, 0)  # Return stored value, default to 0 if unset
        else:
            return int(x)  # Otherwise, it's just a plain number

    def getValue(self, formula: str) -> int:
        # Formula format is always "=X+Y"
        a, b = formula[1:].split("+")  # Remove '=' then split at '+'
        return self.val(a) + self.val(b)  # Evaluate both parts and sum

# Your Spreadsheet object will be instantiated and called as such:
# obj = Spreadsheet(rows)
# obj.setCell(cell,value)
# obj.resetCell(cell)
# param_3 = obj.getValue(formula)
