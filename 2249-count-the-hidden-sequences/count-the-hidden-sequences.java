class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long prefix = 0;
        long minPrefix = 0, maxPrefix = 0;
        
        for (int diff : differences) {
            prefix += diff;
            minPrefix = Math.min(minPrefix, prefix);
            maxPrefix = Math.max(maxPrefix, prefix);
        }
        
        long minStart = lower - minPrefix;
        long maxStart = upper - maxPrefix;
        
        long result = maxStart - minStart + 1;

        return (int)Math.max(result, 0);
    }
}