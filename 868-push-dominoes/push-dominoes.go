func pushDominoes(dominoes string) string {
    arr := []rune(dominoes);
    n := len(arr);
    lastR := -1;

    for i := 0; i < n; i++ {
        if arr[i] == 'L' {
            if lastR == -1 {
                for j := i - 1; j >= 0 && arr[j] == '.'; j-- {
                    arr[j] = 'L';
                }
            } else {
                left, right := i-1, lastR+1;
                for right < left {
                    arr[right] = 'R';
                    arr[left] = 'L';
                    right++;
                    left--;
                }

                lastR = -1;
            }
        } else if arr[i] == 'R' {
            if lastR != -1 {
                for j := lastR + 1; j < i; j++ {
                    arr[j] = 'R';
                }
            }

            lastR = i;
        }
    }

    if lastR != -1 {
        for j := lastR + 1; j < n; j++ {
            arr[j] = 'R';
        }
    }

    return string(arr);
}