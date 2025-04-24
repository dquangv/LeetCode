class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;

        // Đếm số lượng phần tử phân biệt trong toàn mảng
        Set<Integer> totalSet = new HashSet<>();
        for (int num : nums) {
            totalSet.add(num);
        }
        int totalDistinct = totalSet.size();

        int count = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> subSet = new HashSet<>();
            for (int j = i; j < n; j++) {
                subSet.add(nums[j]);
                if (subSet.size() == totalDistinct) {
                    count += (n - j); // Tối ưu hóa: những cái sau j chắc chắn là complete luôn
                    break;
                }
            }
        }

        return count;
    }
}