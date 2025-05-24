func findWordsContaining(words []string, x byte) []int {
    result := []int{};

    for i, word := range words {
        if strings.IndexByte(word, x) != -1 {
            result = append(result, i);
        }
    }
    
    return result;
}