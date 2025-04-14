func countGoodTriplets(arr []int, a int, b int, c int) int {
    n := len(arr);
    result := 0;

    for i := 0; i < n - 2; i++ {
        x := arr[i];

        for j := i + 1; j < n - 1; j++ {
            y := arr[j];

            if abs(x - y) > a {
                continue;
            }

            for k := j + 1; k < n; k++ {
                z := arr[k];

                if abs(y - z) > b {
                    continue;
                }

                if abs(x - z) <= c {
                    result++;
                }
            }
        }
    }

    return result;
}

func abs(x int) int {
    if x > 0 {
        return x;
    }

    return -x;
}