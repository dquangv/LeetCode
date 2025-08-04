public class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        // lastFruit: the type of the last fruit seen
        // secondLastFruit: the other fruit type currently in the basket
        int lastFruit = -1, secondLastFruit = -1;
        // lastCount: count of consecutive lastFruit seen before current index
        int lastCount = 0;
        // currMax: current window size of valid fruits
        int currMax = 0;
        // max: global max of currMax over iterations
        int max = 0;

        for (int i = 0; i < n; i++) {
            int fruit = fruits[i];

            // If current fruit is one of the two types we're currently collecting
            if (fruit == lastFruit || fruit == secondLastFruit)
                currMax++;
            else
                // We encountered a new fruit type not in the current two types
                // So the new window starts from lastCount (repeated lastFruit) + current fruit
                currMax = lastCount + 1;

            // Update lastCount depending on whether fruit is same as lastFruit
            if (fruit == lastFruit)
                lastCount++;
            else {
                // Switch: the last fruit changes, so reset lastCount to 1
                // Shift secondLastFruit and lastFruit types accordingly
                lastCount = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            // Update max fruits collected so far
            max = Math.max(max, currMax);
        }

        return max;
    }
}
