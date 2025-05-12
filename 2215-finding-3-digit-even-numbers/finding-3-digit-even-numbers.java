class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] freqs = new int[10];
        int even = 0;

        for (int digit : digits) {
            freqs[digit]++;
            if (digit % 2 == 0)
                even++;
        }

        if (even == 0)
            return new int[0];

        List<Integer> list = new ArrayList<>();
        for (int x = 100; x <= 999; x += 2) {
            int[] freq = freqs.clone();
            int x0 = x % 10;
            int x1 = (x / 10) % 10;
            int x2 = x / 100;

            if (--freq[x0] < 0)
                continue;
            if (--freq[x1] < 0)
                continue;
            if (--freq[x2] < 0)
                continue;

            list.add(x);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);

        return result;
    }
}