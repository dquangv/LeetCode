class Solution {
    // Tìm chỉ số phần tử đầu tiên không âm trong mảng
    int findPivot(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] >= 0)
                hi = mid - 1; // tìm về bên trái nếu >= 0
            else
                lo = mid + 1;
        }
        return lo; // trả về chỉ số đầu tiên không âm
    }

    // Đếm số lượng cặp (x, y) sao cho x*y <= limit
    long countLE(int[] X, int[] Y, long limit) {
        long cnt = 0;
        int j = Y.length - 1;
        for (int x : X) {
            while (j >= 0 && (long) x * Y[j] > limit)
                j--; // giảm j đến khi tích <= limit
            cnt += j + 1; // có j+1 phần tử thỏa mãn với x
        }
        return cnt;
    }

    // Đảo ngược mảng phần tử âm thành dương và đổi thứ tự để dễ xử lý
    int[] reverseNeg(int[] arr, int len) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++)
            res[i] = -arr[len - 1 - i]; // đảo thứ tự và đổi dấu
        return res;
    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int piv1 = findPivot(nums1); // tách nums1 thành âm và dương
        int piv2 = findPivot(nums2); // tách B thành âm và dương

        // Tách mảng nums1 thành phần dương và âm (đã đổi dấu và thứ tự)
        int[] Apos = Arrays.copyOfRange(nums1, piv1, nums1.length);
        int[] Bpos = Arrays.copyOfRange(nums2, piv2, nums2.length);
        int[] Aneg = reverseNeg(nums1, piv1);
        int[] Bneg = reverseNeg(nums2, piv2);

        // Số lượng tích âm: âm*dương hoặc dương*âm
        int negCnt = Aneg.length * Bpos.length + Apos.length * Bneg.length;
        long sign = 1;

        // Nếu k <= số lượng tích âm => câu trả lời là tích âm
        if (k > negCnt)
            k -= negCnt; // bỏ qua tích âm
        else {
            // Đổi lại hướng tính để làm việc với tích âm như tích dương
            int[] tmp = Bneg;
            Bneg = Bpos;
            Bpos = tmp;
            sign = -1;
            k = negCnt - k + 1; // tính lại vị trí thứ k trong phần tích âm
        }

        // Binary search để tìm giá trị nhỏ nhất mà có >= k tích nhỏ hơn hoặc bằng nó
        long lo = 0, hi = (long) 1e10;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            // Đếm số lượng tích <= mid từ các nhóm:
            // - âm*âm: Aneg * Bneg
            // - dương*dương: Apos * Bpos
            if (countLE(Aneg, Bneg, mid) + countLE(Apos, Bpos, mid) >= k)
                hi = mid; // tìm về trái nếu đủ số lượng
            else
                lo = mid + 1; // cần tìm giá trị lớn hơn
        }

        // Trả kết quả với dấu phù hợp
        return lo * sign;
    }

}