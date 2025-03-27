class Solution {
    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int dominant = -1;
        boolean haveDominant = false;

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            if (countMap.get(num) * 2 > nums.size()) {
                dominant = num;
                haveDominant = true;
            }
        }

        if (!haveDominant) return -1;

        int countDominant = 0;
        
        // for (int i = 0; i < nums.size(); i++) {
        //     if (nums.get(i) == dominant) countDominant++;
        //     if (countDominant * 2 > i  + 1) {
        //         if (nums.size() - i -1 == 1 && nums.get(nums.size() - 1) == dominant) return i;
        //         if ((countMap.get(dominant) - countDominant) * 2 > nums.size() - i - 1) return i;
        //     }
        // }

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == dominant) countDominant++;
            if ((countDominant * 2 > i  + 1) && ((nums.size() - i -1 == 1 && nums.get(nums.size() - 1) == dominant) || ((countMap.get(dominant) - countDominant) * 2 > nums.size() - i - 1))) return i;
        }

        // boolean checkValidSplit = true;
        // int i = 0;
        // while (checkValidSplit) {
        //     if (nums.get(i) == dominant) countDominant++;

        // }

        return -1;
    }
}