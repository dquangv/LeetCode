class Solution {
    int[] parent = new int[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Mỗi chữ cái ban đầu đại diện cho chính nó
        for (int i = 0; i < 26; i++)
            parent[i] = i;

        // Xử lý các cặp tương đương từ s1 và s2
        for (int i = 0; i < s1.length(); i++)
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');

        // Xây kết quả bằng cách thay từng ký tự trong baseStr bằng đại diện nhỏ nhất
        StringBuilder sb = new StringBuilder();
        for (char c : baseStr.toCharArray())
            sb.append((char) (find(c - 'a') + 'a'));

        return sb.toString();
    }

    // Tìm gốc của một phần tử, kèm path compression
    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // Hợp nhất 2 nhóm, chọn ký tự nhỏ hơn làm đại diện
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA < rootB)
            parent[rootB] = rootA;
        else
            parent[rootA] = rootB;
    }
}
