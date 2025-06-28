class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int res[] = new int[k];

        // PriorityQueue `pq` sắp xếp theo giá trị giảm dần (max-heap)
        // Mỗi phần tử là mảng [giá trị, chỉ số]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Đưa toàn bộ phần tử vào pq
        for (int i = 0; i < nums.length; i++)
            pq.offer(new int[]{nums[i], i});

        // PriorityQueue `maxSum` sắp xếp theo chỉ số tăng dần (min-heap theo index)
        // Đảm bảo subsequence đúng thứ tự xuất hiện trong mảng gốc
        PriorityQueue<int[]> maxSum = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int i = 0; i < k; i++)
            maxSum.offer(pq.poll());
        
        for (int i = 0; i < k; i++)
            res[i] = maxSum.poll()[0];

        return res;
    }
}