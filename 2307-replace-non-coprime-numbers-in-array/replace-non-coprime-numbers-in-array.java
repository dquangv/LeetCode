class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int num : nums) {
            // Merge while last element and current num are non-coprime
            while (!res.isEmpty() && gcd(res.get(res.size() - 1), num) > 1) {
                num = lcm(res.get(res.size() - 1), num);
                res.remove(res.size() - 1); // pop last
            }
            res.add(num);
        }

        return res;
    }

    // Helper to compute GCD
    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        
        return a;
    }

    // Helper to compute LCM
    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}