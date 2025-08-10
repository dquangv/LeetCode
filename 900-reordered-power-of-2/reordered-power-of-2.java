class Solution {
    public boolean reorderedPowerOf2(int n) {
        // Get the digit count array for the input number n
        int count[] = Count(n);

        int power = 1; // start from 2^0 = 1
        for (int i = 0; i < 31; i++) { // loop through powers of 2 up to 2^30 (fits in int)
            // Get the digit count array for the current power of two
            int[] powerCount = Count(power);

            // If the digit counts match, n can be rearranged to form this power of two
            if (Equal(count, powerCount))
                return true; // Found a match

            // Move to the next power of two
            power *= 2;
        }
        // No power of two matched n's digits
        return false;
    }

    private int[] Count(int n) {
        // This function counts occurrences of each digit (0–9) in a number
        int Count[] = new int[10]; // array to store digit frequencies

        while (n != 0) {
            Count[n % 10]++; // increment count for last digit
            n /= 10; // remove the last digit
        }

        return Count;
    }

    private boolean Equal(int ar1[], int ar2[]) {
        // Compare two digit frequency arrays
        for (int i = 0; i < ar2.length; i++)
            if (ar1[i] != ar2[i])
                return false;

        return true;
    }

}