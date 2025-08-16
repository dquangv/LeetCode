class Solution {
    public int maximum69Number(int num) {
        

        for (int i = 3; i >= 0; i--) {
            int n = (int) (num / Math.pow(10, i));
            if (n % 10 == 6) {
                num += (int) 3 * Math.pow(10, i);
                break;
            }
        }

        return num;
    }
}