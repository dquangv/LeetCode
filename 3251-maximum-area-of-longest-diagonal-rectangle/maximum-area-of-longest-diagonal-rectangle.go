func areaOfMaxDiagonal(dimensions [][]int) int {
    maxDiag := -1   // store maximum diagonal squared
    maxArea := -1   // store maximum area
    res := 0        // result area

    for _, rect := range dimensions {
        l, w := rect[0], rect[1]
        diag := l*l + w*w
        area := l * w

        // Pick rectangle with larger diagonal,
        // or if equal diagonal, pick the one with larger area
        if diag > maxDiag || (diag == maxDiag && area > maxArea) {
            maxDiag = diag
            maxArea = area
            res = area
        }
    }
    
    return res
}