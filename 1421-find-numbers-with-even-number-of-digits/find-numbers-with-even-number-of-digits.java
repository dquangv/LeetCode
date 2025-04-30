// class Solution {
//     public int findNumbers(int[] nums) {
//         int result = 0;

//         for (int num : nums) {
//             if (num == 100000 || (num < 10000 && num > 999) || (num < 100 && num > 9)) result++;
//         }

//         return result;
//     }
// }


class Solution {
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int x : nums)
            // floor(log10(x)) + 1 = number of digits x
            result += (int)(Math.floor(Math.log10(x)) + 1) % 2 == 0 ? 1 : 0;

        return result;
    }
}