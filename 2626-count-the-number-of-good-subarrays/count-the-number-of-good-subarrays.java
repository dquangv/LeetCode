class Solution {
    public long countGood(int[] nums, int k) {
        if (nums.length < 2) return 0L;
        
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length, left = 0;
        long pairs = 0, result = 0;

        for (int right = 0; right < n; right++) {
            int val = nums[right];
            int count = freq.getOrDefault(val, 0);
            
            pairs += count; // thêm count cặp mới vì mỗi val trước đó sẽ tạo thành 1 cặp với val hiện tại
            freq.put(val, count + 1);

            // Shrink left đến khi số pairs < k
            while (pairs >= k) {
                result += n - right; // tất cả subarray bắt đầu từ left, left+1,... đến right đều "good"

                int leftVal = nums[left];
                int leftCount = freq.get(leftVal);

                pairs -= leftCount - 1;  // khi loại bỏ leftVal, giảm số cặp liên quan đến nó
                freq.put(leftVal, leftCount - 1); // giảm tần suất của leftVal
                left++;
            }
        }

        return result;
    }
}