class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // PriorityQueue: max-heap ordered by the "gain" (increase in pass ratio if we add a student)
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                if (a[0] < b[0])
                    return 1; // bigger gain comes first
                if (a[0] > b[0])
                    return -1;
                return 0;
            }
        });

        // Initialize heap: each class contributes [gain, pass, total]
        for (int i = 0; i < classes.length; i++) {
            double pass = classes[i][0];
            double total = classes[i][1];
            // gain = new ratio - old ratio
            double gain = (pass + 1.0) / (total + 1.0) - pass / total;
            pq.offer(new double[] { gain, pass, total });
        }

        // Distribute extra students one by one
        while (extraStudents > 0) {
            double[] top = pq.poll(); // take the class with the largest gain
            double pass = top[1] + 1; // add one passing student
            double total = top[2] + 1;
            double gain = (pass + 1.0) / (total + 1.0) - pass / total;
            pq.offer(new double[] { gain, pass, total });
            extraStudents--;
        }

        // Calculate final average pass ratio
        double sum = 0.0;
        for (double[] c : pq)
            sum += c[1] / c[2];

        return sum / classes.length;
    }
}