class Solution {
    int[] q = new int[50000];
    int front = 0, back = 0;
    int strength;

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int n = tasks.length, m = workers.length;
        this.strength = strength;

        Arrays.sort(tasks);
        Arrays.sort(workers);

        int l = 0, r = Math.min(n, m);

        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (doable(tasks, workers, mid, pills))
                l = mid;
            else
                r = mid - 1;
        }

        return l;
    }

    private boolean doable(int[] tasks, int[] workers, int k, int pills) {
        int tIdx = 0;
        front = back = 0;

        for (int i = workers.length - k; i < workers.length; i++) {
            int W = workers[i];

            while (tIdx < k && tasks[tIdx] <= W + strength)
                q[back++] = tasks[tIdx++];

            if (front == back)
                return false;

            if (q[front] <= W) 
                front++; // No pill needed
            else {
                if (pills == 0)
                    return false;
                pills--;
                back--; // Use pill on hardest task (from back of queue)
            }
        }
        
        return true;
    }
}