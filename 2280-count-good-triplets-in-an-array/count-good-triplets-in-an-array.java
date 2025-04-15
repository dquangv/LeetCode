class SegmentTree {
    int n;
    int[] tree;

    public SegmentTree(int size) {
        this.n = size;
        this.tree = new int[4 * size];
    }

    private void update(int idx, int l, int r, int pos, int val) {
        if (l == r) {
            tree[idx] += val;
            return;
        }
        
        int mid = (l + r) / 2;
        if (pos <= mid) {
            update(2 * idx, l, mid, pos, val);
        } else {
            update(2 * idx + 1, mid + 1, r, pos, val);
        }

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }

    private int query(int idx, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return tree[idx];
        int mid = (l + r) / 2;

        return query(2 * idx, l, mid, ql, qr) + query(2 * idx + 1, mid + 1, r, ql, qr);
    }

    public void add(int pos, int val) {
        update(1, 0, n - 1, pos, val);
    }

    public int sum(int pos) {
        return query(1, 0, n - 1, 0, pos);
    }
}

public class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int[] id2 = new int[n];
        for (int i = 0; i < n; i++) {
            id2[nums2[i]] = i;
        }

        int[] inv = new int[n];
        for (int i = 0; i < n; i++) {
            inv[id2[nums1[i]]] = i;
        }

        SegmentTree tree = new SegmentTree(n);
        long ans = 0;

        for (int x = 0; x < n; x++) {
            int pos = inv[x];
            long L = tree.sum(pos);
            tree.add(pos, 1);
            long R = (n - 1 - pos) - (x - L);
            ans += L * R;
        }

        return ans;
    }
}
