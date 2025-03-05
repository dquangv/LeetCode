class Solution {
    public long coloredCells(int n) {
        long num = (long) n;
        
        return 2 * (num - 1) * (num - 1) + 2 * (num - 1) + 1;
    }
}