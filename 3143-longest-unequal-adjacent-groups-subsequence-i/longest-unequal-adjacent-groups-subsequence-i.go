func getLongestSubsequence(words []string, groups []int) []string {
    if len(words) == 0 {
        return []string{};
    }

    result := []string{words[0]};
    flag := groups[0];

    for i := 0; i < len(groups); i++ {
        if flag != groups[i] {
            result = append(result, words[i]);
            flag = groups[i];
        }
    }

    return result;
}