class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int N = (int)Math.sqrt(100000);
    private static boolean[] isPrime = new boolean[N + 1];
    private static List<Integer> prime = new ArrayList<>();
    private static long[] num_idx = new long[100000];
    
    static {
        Arrays.fill(isPrime, true);
        Sieve();
    }
    
    // Tìm số nguyên tố
    private static void Sieve() {
        if (!prime.isEmpty()) return;

        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                prime.add(i);
                for (int j = i * i; j <= N; j += i)
                    isPrime[j] = false;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) prime.add(i);
        }
    }
    
    // Tính số lượng thừa số nguyên tố phân biệt của x
    private static int prime_score(int x) {
        if (x <= N && isPrime[x]) return 1;

        int xsqrt = (int)Math.sqrt(x);
        int cnt = 0;
        for (int p : prime) {
            if (p > xsqrt) break;
            if (x % p != 0) continue;
            while (x % p == 0) {
                x /= p;
            }
            cnt++;
        }

        cnt += (x > 1) ? 1 : 0;

        return cnt;
    }
    
    // Hàm tính lũy thừa theo modulo MOD
    private static long modPow(long x, int exp) {
        long ans = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            exp /= 2;
        }
        return ans;
    }
    
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        
        int[] score = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        
        for (int i = 0; i < n; i++) {
            score[i] = prime_score(nums.get(i));
        }
        
        // Calculate left boundaries
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && score[i] > score[st.peekLast()]) {
                st.pollLast();
            }
            left[i] = st.isEmpty() ? -1 : st.peekLast();
            st.addLast(i);
        }
        
        // Calculate right boundaries
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && score[i] >= score[st.peekLast()]) {
                st.pollLast();
            }
            right[i] = st.isEmpty() ? n : st.peekLast();
            st.addLast(i);
        }
        
        // Fill num_idx array
        for (int i = 0; i < n; i++) {
            num_idx[i] = ((long)nums.get(i) << 20) + i;
        }
        
        // Sort in descending order
        Arrays.sort(num_idx, 0, n);
        reverse(num_idx, 0, n - 1);
        
        long ans = 1;
        for (int i = 0; i < n && k > 0; i++) {
            long nI = num_idx[i];
            int x = (int)(nI >> 20);
            int idx = (int)(nI & ((1 << 20) - 1));
            long exp = Math.min((long)(idx - left[idx]) * (right[idx] - idx), (long)k);
            ans = ans * modPow(x, (int)exp) % MOD;
            k -= exp;
        }
        
        return (int)ans;
    }
    
    private void reverse(long[] arr, int start, int end) {
        while (start < end) {
            long temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}