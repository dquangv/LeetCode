class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int result = 0, n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            int x = arr[i];

            for (int j = i + 1; j < n - 1; j++) {
                int y = arr[j];

                if (Math.abs(x - y) <= a) {
                    for (int k = j + 1; k < n; k++) {
                        int z = arr[k];

                        if (Math.abs(y - z) <= b && Math.abs(z - x) <= c)
                            result++;
                    }
                }
            }
        }

        return result;
    }
}