func checkValidCuts(n int, rectangles [][]int) bool {

    for k := range 2 {

        sort.Slice(rectangles, func(i, j int) bool {
            return rectangles[i][k] < rectangles[j][k];
        })

        var first bool;

        end := rectangles[0][k + 2];

        for _, rect := range rectangles {

            if rect[k] >= end {
                if first {
                    return true;
                }

                first = true;
            }

            end = max(end, rect[k + 2]);
        }

    }

    return false;
}