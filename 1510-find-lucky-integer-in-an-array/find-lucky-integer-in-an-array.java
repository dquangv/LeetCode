class Solution {
    public int findLucky(int[] arr) {
        // int[] freq = new int[501];
        // int max = 0, result = -1;
        // for (int i = 0; i < arr.length; i ++) {
        //     freq[i]++;
        //     max = Math.max(arr[i], max);
        // }

        // for (int i = max; i > 0; i--) {
        //     if (i == freq[i]) {
        //         result = i;
        //         break;
        //     }
        // }

        // return result;
        int result = -1;
        
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

        return result;
    }
}