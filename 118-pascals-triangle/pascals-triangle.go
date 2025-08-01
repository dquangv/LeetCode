func generate(numRows int) [][]int {
	if numRows == 0 {
		return [][]int{}
	}
	if numRows == 1 {
		return [][]int{{1}}
	}

	prevRows := generate(numRows - 1)
	newRow := []int{1}

	for i := 1; i < numRows-1; i++ {
		prev := prevRows[numRows-2]
		newRow = append(newRow, prev[i-1]+prev[i])
	}

	newRow = append(newRow, 1)
	prevRows = append(prevRows, newRow)
	return prevRows
}
