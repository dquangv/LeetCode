class Solution {
    public boolean judgePoint24(int[] cards) {
        // Convert to doubles since we need real division
        List<Double> nums = new ArrayList<>();
        for (int v : cards)
            nums.add((double) v);

        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        // Base case: only one number left
        if (nums.size() == 1) 
            return Math.abs(nums.get(0) - 24) < 1e-6;

        int n = nums.size();
        // Try all pairs (i, j)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double num1 = nums.get(i), num2 = nums.get(j);

                // Build nextNums without i, j
                List<Double> nextNums = new ArrayList<>();
                for (int k = 0; k < n; k++)
                    if (k != i && k != j)
                        nextNums.add(nums.get(k));

                // Possible results with +, -, *
                double[] candidates = {
                        num1 + num2,
                        num1 - num2,
                        num2 - num1,
                        num1 * num2
                };

                for (double val : candidates) {
                    nextNums.add(val);
                    if (dfs(nextNums))
                        return true;
                    nextNums.remove(nextNums.size() - 1); // backtrack
                }

                // Division (check zero)
                if (Math.abs(num2) > 1e-6) {
                    nextNums.add(num1 / num2);
                    if (dfs(nextNums))
                        return true;
                    nextNums.remove(nextNums.size() - 1);
                }

                if (Math.abs(num1) > 1e-6) {
                    nextNums.add(num2 / num1);
                    if (dfs(nextNums))
                        return true;
                    nextNums.remove(nextNums.size() - 1);
                }
            }
        }
        
        return false;
    }
}