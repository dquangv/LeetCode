func maxCollectedFruits(fruits [][]int) int {
    n := len(fruits)

    // Step 1: Mark inaccessible cells with 0
    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            if i < j && j < n-1-i {
                fruits[i][j] = 0
            }
            if j < i && i < n-1-j {
                fruits[i][j] = 0
            }
        }
    }

    // Step 2: First child - diagonal path
    res := 0
    for i := 0; i < n; i++ {
        res += fruits[i][i]
    }

    // Step 3: Second child - top-right to bottom-right
    for i := 1; i < n; i++ {
        for j := i + 1; j < n; j++ {
            maxVal := fruits[i-1][j-1]
            if fruits[i-1][j] > maxVal {
                maxVal = fruits[i-1][j]
            }
            if j+1 < n && fruits[i-1][j+1] > maxVal {
                maxVal = fruits[i-1][j+1]
            }
            fruits[i][j] += maxVal
        }
    }

    // Step 4: Third child - bottom-left to bottom-right
    for j := 1; j < n; j++ {
        for i := j + 1; i < n; i++ {
            maxVal := fruits[i-1][j-1]
            if fruits[i][j-1] > maxVal {
                maxVal = fruits[i][j-1]
            }
            if i+1 < n && fruits[i+1][j-1] > maxVal {
                maxVal = fruits[i+1][j-1]
            }
            fruits[i][j] += maxVal
        }
    }

    return res + fruits[n-1][n-2] + fruits[n-2][n-1]
}