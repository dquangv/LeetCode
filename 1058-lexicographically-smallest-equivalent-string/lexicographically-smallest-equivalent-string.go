func smallestEquivalentString(s1 string, s2 string, baseStr string) string {
    parent := make([]int, 26);
    // Mỗi ký tự đại diện cho chính nó ban đầu
    for i := 0; i < 26; i++ {
        parent[i] = i;
    }

    // Tìm gốc đại diện của ký tự, dùng path compression
    var find func(x int) int;
    find = func(x int) int {
        if parent[x] != x {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Hợp nhất 2 nhóm tương đương
    union := func(a, b int) {
        pa := find(a);
        pb := find(b);
        if pa < pb {
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
        }
    }

    // Duyệt từng cặp ký tự từ s1 và s2 để hợp nhóm
    for i := 0; i < len(s1); i++ {
        union(int(s1[i]-'a'), int(s2[i]-'a'));
    }

    // Tạo kết quả bằng cách lấy đại diện nhỏ nhất tương đương cho từng ký tự
    result := make([]byte, len(baseStr));
    for i := 0; i < len(baseStr); i++ {
        result[i] = byte(find(int(baseStr[i]-'a')) + 'a');
    }

    return string(result);
}