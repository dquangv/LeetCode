func lengthOfLastWord(s string) int {
    s = strings.TrimSpace(s);

    result := 0;
    for i := len(s) - 1; i >= 0; i-- {
        if s[i] == ' ' {
            break;
        }

        result++;
    }

    return result;
}