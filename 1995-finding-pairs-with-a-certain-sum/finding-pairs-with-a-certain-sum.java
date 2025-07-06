class FindSumPairs {
    private int[] nums1, nums2;
    private Map<Integer, Integer> freq;
    private int xMax; // Current maximum value in nums2 (for optimization)

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.freq = new HashMap<>();
        this.xMax = 0;

        // Count frequency of elements in nums2
        for (int x : nums2) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            xMax = Math.max(xMax, x);
        }

        // Sort nums1 to allow binary search optimization later
        Arrays.sort(this.nums1);
    }

    public void add(int index, int val) {
        int oldVal = nums2[index];

        // Decrease the frequency of the old value
        freq.put(oldVal, freq.get(oldVal) - 1);
        if (freq.get(oldVal) == 0)
            freq.remove(oldVal);

        // Update nums2 and frequency map
        nums2[index] += val;
        int newVal = nums2[index];
        freq.put(newVal, freq.getOrDefault(newVal, 0) + 1);

        // Update xMax if needed
        if (newVal > xMax)
            xMax = newVal;
    }

    public int count(int tot) {
        int count = 0;

        // Optimization: Only consider nums1[i] >= tot - xMax
        int i0 = lowerBound(nums1, Math.max(1, tot - xMax));

        for (int i = i0; i < nums1.length; i++) {
            int y = nums1[i];
            if (y >= tot)
                break;

            // Check if there's a value in nums2 such that nums1[i] + nums2[j] == tot
            int required = tot - y;
            count += freq.getOrDefault(required, 0);
        }

        return count;
    }

    // Helper function to simulate lower_bound (first index >= target)
    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid;

        }

        return left;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */