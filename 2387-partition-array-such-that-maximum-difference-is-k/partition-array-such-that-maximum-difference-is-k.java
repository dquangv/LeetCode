class Solution {
    public int partitionArray(int[] nums, int k) {
        int[] freq = new int[(int) (Math.pow(10, 5) + 1)];
        int max = 0, result = 0;

        for (int num : nums) {
            freq[num]++;
            max = Math.max(max, num);
        }

        for (int i = 0; i <= max; i++)
            if (freq[i] > 0) {
                result++; // Mỗi lần gặp phần tử chưa được phân nhóm => tạo 1 subsequence mới
                i += k;
                /* Vì subsequence chỉ chứa các phần tử có hiệu max - min <= k,
                nên ta bỏ qua k phần tử tiếp theo (i += k)
                Điều này đảm bảo các subsequence không chồng lấn nhau theo điều kiện đã cho */
            }

        return result; // Trả về tổng số subsequence
    }
}