class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Long> countMap = new HashMap<>();
        countMap.put(0, 1L); // base case: prefix = 0

        int prefix = 0;
        long result = 0;

        for (int num : nums) {
            // nếu nums[i] % modulo == k thì tăng prefix
            if (num % modulo == k) {
                prefix++;
            }

            // cần tìm: (prefix - k + modulo) % modulo
            int key = (prefix - k + modulo) % modulo;
            result += countMap.getOrDefault(key, 0L);

            // lưu lại prefix % modulo để sau này sử dụng
            int currentMod = prefix % modulo;
            countMap.put(currentMod, countMap.getOrDefault(currentMod, 0L) + 1);
        }

        return result;
    }
}