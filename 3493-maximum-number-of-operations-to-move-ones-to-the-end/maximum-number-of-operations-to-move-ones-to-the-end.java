public class Solution {
    public int maxOperations(String binaryString) {
        char[] binaryArray = binaryString.toCharArray();
        int countOnes = binaryArray[0] - '0';
        int maxOperations = 0;

        for (int i = 1; i < binaryArray.length; ++i) {
            countOnes += binaryArray[i] - '0';
            maxOperations += binaryArray[i - 1] > binaryArray[i] ? countOnes : 0;
        }
        
        return maxOperations;
    }
}