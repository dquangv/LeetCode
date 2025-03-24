func countDays(days int, meetings [][]int) int {
    sort.Slice(meetings, func(i, j int) bool {
		return meetings[i][0] < meetings[j][0];
	})

	cntDays := meetings[0][0] - 1;
	end := meetings[0][1];

	for indx := 1; indx < len(meetings); indx++ {
		start := meetings[indx][0];

		if start > end {
			cntDays += start - end - 1;
		}
        
		if meetings[indx][1] > end {
			end = meetings[indx][1];
		}
	}

	if end < days {
		return days - end + cntDays;
	}

	return cntDays;
}