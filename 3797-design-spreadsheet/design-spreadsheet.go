// Spreadsheet struct to hold rows and cells
type Spreadsheet struct {
	rows  int
	cells map[string]int
}

// Constructor initializes the spreadsheet with given rows
func Constructor(rows int) Spreadsheet {
	return Spreadsheet{
		rows:  rows,
		cells: make(map[string]int),
	}
}

// SetCell sets the value of a given cell (e.g., "A1")
func (this *Spreadsheet) SetCell(cell string, value int) {
	this.cells[cell] = value
}

// ResetCell resets a given cell to 0
func (this *Spreadsheet) ResetCell(cell string) {
	this.cells[cell] = 0
}

// helper function to resolve either a cell reference or an integer
func (this *Spreadsheet) val(x string) int {
	if len(x) > 0 && x[0] >= 'A' && x[0] <= 'Z' {
		// It's a cell reference
		if v, ok := this.cells[x]; ok {
			return v
		}
		return 0
	}
	// It's a plain integer
	num, _ := strconv.Atoi(x)
	return num
}

// GetValue evaluates formula of form "=X+Y"
func (this *Spreadsheet) GetValue(formula string) int {
	// Remove leading "=" and split by "+"
	parts := strings.Split(formula[1:], "+")
	a, b := parts[0], parts[1]
	return this.val(a) + this.val(b)
}