class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        int left = 0;

        for (int i = 1; i < nums.length - 1; i++)
            // Skip if current number is the same as the next one (flat region)
            if (nums[i] != nums[i + 1]) {

                // Check if nums[i] forms a hill or valley with its left and right neighbors
                if ((nums[i] > nums[left] && nums[i] > nums[i + 1]) || // Hill condition
                        (nums[i] < nums[left] && nums[i] < nums[i + 1])) // Valley condition
                    count++; // Increase the count for hill or valley

                // Update 'left' to current index for the next comparison
                left = i;
            }

        return count; // Return the total number of hills and valleys
    }
}
