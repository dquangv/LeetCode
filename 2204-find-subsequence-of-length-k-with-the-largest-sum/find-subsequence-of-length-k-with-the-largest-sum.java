class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;

        // Pair chứa giá trị và chỉ số ban đầu để giữ thứ tự
        List<int[]> numWithIndex = new ArrayList<>();
        for (int i = 0; i < n; i++)
            numWithIndex.add(new int[] { nums[i], i });

        // Chọn top k phần tử có giá trị lớn nhất bằng cách sắp xếp giảm dần theo giá trị
        numWithIndex.sort((a, b) -> b[0] - a[0]);

        // Lấy k phần tử đầu tiên (có giá trị lớn nhất)
        List<int[]> topK = numWithIndex.subList(0, k);

        // Sắp xếp lại theo thứ tự chỉ số ban đầu để giữ thứ tự subsequence đúng
        topK.sort(Comparator.comparingInt(a -> a[1]));

        // Tạo mảng kết quả với giá trị từ các phần tử đã chọn
        int[] result = new int[k];
        for (int i = 0; i < k; i++)
            result[i] = topK.get(i)[0];

        return result;
    }
}