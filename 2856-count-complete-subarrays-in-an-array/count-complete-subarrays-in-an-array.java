class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> distinctSet = new HashSet<>();
        for (int val : nums) {
            distinctSet.add(val); // Tập hợp tất cả giá trị khác nhau
        }

        int totalUnique = distinctSet.size(); // Tổng số phần tử khác nhau
        int left = 0;
        int right = 0;
        int count = 0;

        Map<Integer, Integer> windowFreq = new HashMap<>();

        while (right < nums.length) {
            windowFreq.put(nums[right], windowFreq.getOrDefault(nums[right], 0) + 1);

            while (windowFreq.size() == totalUnique) {
                count += (nums.length - right); // Mỗi subarray bắt đầu từ left đến right, mở rộng đến cuối đều hợp lệ

                windowFreq.put(nums[left], windowFreq.get(nums[left]) - 1);
                if (windowFreq.get(nums[left]) == 0) {
                    windowFreq.remove(nums[left]);
                }
                left++;
            }
            right++;
        }

        return count;
    }
}