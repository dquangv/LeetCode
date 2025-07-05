class Solution {
    public int findLucky(int[] arr) {
        int result = 0;
        
        for (int i = arr.length - 1; i >= 0; i--) {
            int freq = 0, val = i + 1;
            for (int num : arr) {
                if (num == val) freq++;
                if (freq > val) break;                
            }

            if (freq == val) {
                result = val;
                break;
            }
        }

        return result == 0 ? -1 : result;
    }
}