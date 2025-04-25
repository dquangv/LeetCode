class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        if (k > n)
            return 0;

        // Mảng lưu số lần xuất hiện của từng giá trị prefixMod (prefix % modulo)
        int[] prefixCount = new int[n + 1];
        prefixCount[0] = 1; // base case: tổng trước chưa có gì thì prefix = 0

        long result = 0;
        int prefixModSum = 0;

        for (int num : nums) {
            num %= modulo;

            // Nếu num % modulo == k thì prefix tăng lên
            if (num == k)
                prefixModSum++;

            // Tính prefix % modulo
            prefixModSum %= modulo;

            // Tính giá trị cần tìm trong prefix trước đó: (prefixModSum - k + modulo) % modulo
            int targetMod = (prefixModSum - k + modulo) % modulo;

            if (targetMod < 0)
                targetMod += modulo;

            if (targetMod < n)
                result += prefixCount[targetMod];// Cộng số lượng prefix trước đó thỏa điều kiện vào kết quả

            // Cập nhật số lần xuất hiện của prefix hiện tại
            prefixCount[prefixModSum]++;
        }

        return result;
    }
}