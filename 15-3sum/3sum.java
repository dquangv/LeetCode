class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        // Step 1: Sort the array to make duplicate handling and two-pointer approach easier
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Two-pointer setup: one at the left of remaining array, one at the right
            int left = i + 1, right = nums.length - 1;

            // Step 3: Move the two pointers inward until they meet
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];

                if (total == 0) {
                    // Found a triplet, add it to the result
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move left pointer forward if next value is same (skip duplicates)
                    while (left < right && nums[left] == nums[left + 1])
                        left++;

                    // Move right pointer backward if next value is same (skip duplicates)
                    while (left < right && nums[right] == nums[right - 1])
                        right--;

                    // Move both pointers after processing this triplet
                    left++;
                    right--;
                } else if (total < 0)
                    // If sum is too small, move left pointer forward to increase sum
                    left++;
                else
                    // If sum is too large, move right pointer backward to decrease sum
                    right--;
            }
        }

        // Return all unique triplets
        return res;
    }
}