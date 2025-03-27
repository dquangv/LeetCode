class Solution {
    public int minimumIndex(List<Integer> nums) {
        // Map<Integer, Integer> countMap = new HashMap<>();
        // int dominant = -1;
        // boolean haveDominant = false;

        // for (int num : nums) {
        // countMap.put(num, countMap.getOrDefault(num, 0) + 1);

        // if (countMap.get(num) * 2 > nums.size()) {
        // dominant = num;
        // haveDominant = true;
        // }
        // }

        // if (!haveDominant)
        // return -1;

        int dominant = -1;
        int count = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (count == 0) {
                dominant = nums.get(i);
                count++;
            } else if (nums.get(i) == dominant) {
                count++;
            } else {
                count--;
            }
        }

        int maxCount = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == dominant) {
                maxCount++;
            }
        }

        int countDominant = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == dominant)
                countDominant++;

            if ((countDominant * 2 > i + 1) && ((nums.size() - i - 1 == 1 && nums.get(nums.size() - 1) == dominant)
                    || ((maxCount - countDominant) * 2 > nums.size() - i - 1)))
                return i;
        }

        return -1;
    }
}