class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]); // Each single element is a valid subarray

            // Traverse backward from i-1 to 0 to form subarrays ending at i
            for (int j = i - 1; j >= 0; j--) {
                // Optimization: if OR with arr[i] doesn't change arr[j], break early
                if ((arr[i] | arr[j]) == arr[j])
                    break;

                arr[j] |= arr[i]; // Update arr[j] to OR result of arr[j..i]
                set.add(arr[j]); // Add this result to the set
            }
        }

        return set.size();
    }
}
