class Solution {
    public String pushDominoes(String dominoes) {
        char[] arr = dominoes.toCharArray();
        int n = arr.length;
        int lastR = -1; // lưu vị trí domino 'R' gần nhất

        for (int i = 0; i < n; i++) {
            if (arr[i] == 'L') {
                if (lastR == -1) {
                    // không có R trước đó, tất cả trước L => L
                    for (int j = i - 1; j >= 0 && arr[j] == '.'; --j)
                        arr[j] = 'L';
                } else {
                    // có đoạn 'R...L' => xử lý đối xứng
                    int left = i - 1, right = lastR + 1;
                    // trường hợp có 1 dominoes ở giữa chịu lực L và R cân bằng thì trường hợp đó là right = left (không chạy vòng lặp) => giữ nguyên '.'
                    while (right < left) {
                        arr[right++] = 'R';
                        arr[left--] = 'L';
                    }

                    // reset sau khi xử lý xong
                    lastR = -1;
                }
            } else if (arr[i] == 'R') {
                if (lastR != -1) 
                    // trường hợp 2 R liên tiếp thì toàn bộ '.' ở giữa đều R
                    for (int j = lastR + 1; j < i; ++j) arr[j] = 'R';

                // cập nhật vị trí R gần nhất
                lastR = i;
            }
        }

        // trường hợp vẫn còn 1 'R' chưa xử lý sau khi duyệt hết
        if (lastR != -1) for (int j = lastR + 1; j < n; j++) arr[j] = 'R';

        return new String(arr);
    }
}