class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1]; // Mảng đánh dấu theo kỹ thuật "difference array"
        List<Integer> result = new ArrayList<>();

        // Đánh dấu các đoạn ảnh hưởng bởi key trong khoảng [i-k, i+k]
        for (int i = 0; i < n; i++)
            if (nums[i] == key) {
                int start = Math.max(0, i - k); // Không để âm chỉ số
                int end = Math.min(n, i + k + 1); // end không vượt quá n
                diff[start]++; // Bắt đầu vùng ảnh hưởng
                if (end < n)
                    diff[end]--; // Kết thúc vùng ảnh hưởng
            }

        int sum = 0;
        // Tính tổng tích lũy để biết vùng nào bị ảnh hưởng
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum > 0)
                result.add(i); // Nếu sum > 0 thì i nằm trong vùng k-distant
        }

        return result;
    }
}