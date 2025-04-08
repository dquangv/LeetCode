class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> distinct = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            if (distinct.containsKey(nums[i])) {
                if ((i + 1) % 3 ==0) {
                    return (i + 1) / 3;
                } else {
                    return (i + 1) / 3 + 1;
                }
            }

            distinct.put(nums[i], 1);
        }

        return 0;
    }
}