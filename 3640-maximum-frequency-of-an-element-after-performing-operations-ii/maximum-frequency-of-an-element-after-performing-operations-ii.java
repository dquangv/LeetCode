class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            list.add(new int[] { num - k, 1 });
            list.add(new int[] { num + k + 1, -1 });
        }

        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] l : list) 
            ts.add(l[0]);

        for (int num : map.keySet()) 
            ts.add(num);

        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        int idx = 0;
        int cur = 0, max = 0;
        for (int t : ts) {
            while (idx < list.size() && list.get(idx)[0] <= t) {
                cur += list.get(idx)[1];
                idx++;
            }
            int val = map.getOrDefault(t, 0);
            max = Math.max(max, val + Math.min(numOperations, cur - val));
        }

        return max;
    }
}