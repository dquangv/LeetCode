func numberOfArrays(differences []int, lower int, upper int) int {
    var start, maxVal, minVal int64 = 0, 0, 0;

    for _, diff := range differences {
        start += int64(diff);

        if start > maxVal {
            maxVal = start;
        }

        if start < minVal {
            minVal = start;
        }
    }

    rangeDiff := int64(upper - lower);
    valid := rangeDiff - (maxVal - minVal) + 1;

    if valid < 0 {
        return 0;
    }

    return int(valid);
}