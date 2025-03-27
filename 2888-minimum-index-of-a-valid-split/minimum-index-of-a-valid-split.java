class Solution {
    public int minimumIndex(List<Integer> nums) {
        /*
         * Map<Integer, Integer> countMap = new HashMap<>();
         * int dominant = -1;
         * boolean haveDominant = false;
         * 
         * for (int num : nums) {
         *  countMap.put(num, countMap.getOrDefault(num, 0) + 1);
         * 
         *  if (countMap.get(num) * 2 > nums.size()) {
         *      dominant = num;
         *      haveDominant = true;
         *  }
         * }
         * 
         * if (!haveDominant) return -1;
         * 
         * int countDominant = 0;
         * 
         * for (int i = 0; i < nums.size(); i++) {
         *  if (nums.get(i) == dominant) countDominant++;
         *
         *  if ((countDominant * 2 > i + 1) && ((nums.size() - i -1 == 1 &&
         *  nums.get(nums.size() - 1) == dominant) || ((countMap.get(dominant) -
         *  countDominant) * 2 > nums.size() - i - 1))) return i;
         * }
         * 
         * return -1;
         */

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

            if ((countDominant * 2 > i + 1) &&
                    ((maxCount - countDominant) * 2 > nums.size() - i - 1))
                return i;
        }

        return -1;
    }
}

/*
 * Số lần duyệt không quan trọng bằng tốc độ truy xuất bộ nhớ
 * HashMap(get): truy xuất rải rác, mất thời gian tìm kiếm; Cache locality kém
 * (nhiều cache miss); Không thể tối ưu tốt CPU
 * Boyer-Moore: truy xuất liên tục, đọc tuần tự; Cache locality tốt (cache hit
 * cao); Có thể tối ưu CPU qua pipelining
 * 
 * Bài học rút ra: Hiệu suất của thuật toán không chỉ phụ thuộc vào số lần duyệt
 * (O(N)) mà còn bị ảnh hưởng bởi cách truy xuất bộ nhớ và tối ưu của CPU.
 */