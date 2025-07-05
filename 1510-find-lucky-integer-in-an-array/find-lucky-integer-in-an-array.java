class Solution {
    public int findLucky(int[] arr) {
        int[] freq = new int[501];
        int max = 0, result = -1;
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
            max = Math.max(arr[i], max);
        }

        for (int i = max; i > 0; i--) {
            if (i == freq[i]) {
                result = i;
                break;
            }
        }

        return result;
    }
}