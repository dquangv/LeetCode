class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos2 = new int[n];
        int[] mapping = new int[n];

        // Tạo ánh xạ từ số -> vị trí trong nums2
        for (int i = 0; i < n; i++) {
            pos2[nums2[i]] = i;
        }

        // Tạo mảng vị trí nums1 theo thứ tự nums2
        for (int i = 0; i < n; i++) {
            mapping[pos2[nums1[i]]] = i;
        }

        FenwickTree tree = new FenwickTree(n);
        long result = 0;

        for (int value = 0; value < n; value++) {
            int pos = mapping[value];

            int left = tree.query(pos); // Đếm số phần tử bên trái nhỏ hơn pos
            tree.update(pos, 1);        // Đánh dấu phần tử đã xuất hiện

            int right = (n - 1 - pos) - (value - left); // Số phần tử hợp lệ bên phải
            result += (long) left * right;
        }

        return result;
    }

    private class FenwickTree {
        int[] tree;

        public FenwickTree(int size) {
            tree = new int[size + 1];
        }

        public void update(int index, int delta) {
            index++;
            while (index < tree.length) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        public int query(int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
}
