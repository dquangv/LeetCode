func maxDifference(s string) int {
    freq := make([]int, 26);
    for _, c := range s {
        freq[c - 'a']++;
    }

    a1 := -1 << 31;
    a2 := 1 << 31 - 1;

    for _, f := range freq {
        if f == 0 {
            continue;
        }

        if f % 2 == 0 {
            if f < a2 {
                a2 = f;
            }
        } else {
            if f > a1 {
                a1 = f;
            }
        }
    }

    if a1 == -1 << 31 || a2 == 1 << 31 - 1 {
        return -1;
    }

    return a1 - a2;
}