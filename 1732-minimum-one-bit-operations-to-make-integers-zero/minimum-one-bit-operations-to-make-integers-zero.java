class Solution {
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        int mul = 1;
        while (n > 0) {
            int shift = (int) Math.floor((Math.log(n) / Math.log(2)));
            ans += ((1 << (shift + 1)) - 1) * mul;
            n -= 1 << shift;
            mul *= -1;
        }
        
        return ans;
    }
}