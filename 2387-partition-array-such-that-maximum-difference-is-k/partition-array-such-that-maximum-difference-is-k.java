class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);

        int result = 0, i = 0;

        while (i < nums.length) {
            int start = nums[i]; // Bắt đầu subsequence mới từ phần tử hiện tại
            result++; // Tăng số subsequence lên

            // Duyệt tiếp các phần tử cho đến khi chênh lệch vượt quá k
            // Tất cả phần tử thỏa điều kiện max - min <= k sẽ nằm trong cùng một subsequence
            while (i < nums.length && nums[i] - start <= k)
                i++;
        }

        return result;
    }
}