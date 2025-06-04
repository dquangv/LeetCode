func answerString(word string, numFriends int) string {
    // Nếu chỉ có 1 người bạn thì không cần chia gì cả, trả về nguyên chuỗi
    if numFriends == 1 {
        return word;
    }

    n := len(word);
    maxStr := "";

    for i := 0; i < n; i++ {
        // Tính độ dài tối đa có thể lấy từ vị trí i mà vẫn chia được cho numFriends phần
        subLen := min(n - numFriends + 1, n - i);
        sub := word[i : i+subLen];

        // So sánh theo từ điển, giữ lại chuỗi lớn nhất
        if sub > maxStr {
            maxStr = sub;
        }
    }

    return maxStr;
}