class Solution {
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int mod = 1_000_000_007;

        Arrays.sort(nums);

        // Tiền xử lý mảng power để lưu 2^i % mod (vì có thể có nhiều subsequence)
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; ++i)
            power[i] = (power[i - 1] * 2) % mod;

        int answer = 0;

        // Duyệt từng phần tử làm phần tử nhỏ nhất (nums[left])
        for (int left = 0; left < n; ++left) {
            int remaining = target - nums[left]; // cần nums[right] <= remaining
            int right = binarySearchRightmost(nums, remaining) - 1;

            // Nếu tìm được cặp (left, right) hợp lệ
            if (left <= right)
                // Số lượng subsequence từ left đến right là 2^(right-left)
                answer = (answer + power[right - left]) % mod;
        }

        return answer; // kết quả cuối cùng
    }

    // Hàm tìm chỉ số bên phải nhất (rightmost index) sao cho nums[i] <= target
    public static int binarySearchRightmost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target)
                left = mid + 1; // tiếp tục tìm bên phải
            else
                right = mid - 1; // thu hẹp bên trái
        }
        return left; // trả về chỉ số đầu tiên mà nums[i] > target
    }
}