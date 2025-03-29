const (
	MOD = 1000000007;
	N = 316; // sqrt(100000) ≈ 316
)

var (
	isPrime = make([]bool, N + 1);
	prime []int
	numIdx = make([]int64, 100000);
)

func init() {
	for i := range isPrime {
		isPrime[i] = true;
	}

	sieve();
}

// Eratosthenes' Sieve
func sieve() {
	if len(prime) > 0 {
		return;
	}

	isPrime[0] = false;
	isPrime[1] = false;

	for i := 2; i*i <= N; i++ {
		if isPrime[i] {
			prime = append(prime, i);
			for j := i * i; j <= N; j += i {
				isPrime[j] = false;
			}
		}
	}

	for i := 2; i <= N; i++ {
		if isPrime[i] {
			prime = append(prime, i);
		}
	}
}

// Computing prime score
func primeScore(x int) int {
	if x <= N && isPrime[x] {
		return 1;
	}

	xsqrt := int(math.Sqrt(float64(x)));
	cnt := 0;
	temp := x;
	for _, p := range prime {
		if p > xsqrt {
			break;
		}

		if temp%p != 0 {
			continue;
		}

		for temp%p == 0 {
			temp /= p;
		}
        
		cnt++;
	}

	if temp > 1 {
		cnt++;
	}

	return cnt;
}

// Modular exponentiation
func modPow(x int64, exp int) int64 {
	ans := int64(1);
	base := x;

	for exp > 0 {
		if exp&1 == 1 {
			ans = (ans * base) % MOD;
		}

		base = (base * base) % MOD;
		exp >>= 1;
	}

	return ans;
}

func maximumScore(nums []int, k int) int {
	n := len(nums);

	score := make([]int, n);
	left := make([]int, n);
	right := make([]int, n);

	// Calculate prime scores
	for i := 0; i < n; i++ {
		score[i] = primeScore(nums[i]);
	}

	// Calculate left boundaries
	st := make([]int, 0);
	for i := 0; i < n; i++ {
		for len(st) > 0 && score[i] > score[st[len(st)-1]] {
			st = st[:len(st)-1];
		}

		if len(st) == 0 {
			left[i] = -1;
		} else {
			left[i] = st[len(st)-1];
		}

		st = append(st, i);
	}

	// Calculate right boundaries
	st = st[:0];
	for i := n - 1; i >= 0; i-- {
		for len(st) > 0 && score[i] >= score[st[len(st)-1]] {
			st = st[:len(st)-1];
		}

		if len(st) == 0 {
			right[i] = n;
		} else {
			right[i] = st[len(st)-1];
		}

		st = append(st, i);
	}

	// Fill numIdx array
	for i := 0; i < n; i++ {
		numIdx[i] = int64(nums[i])<<20 + int64(i);
	}

	// Sort in descending order
	sort.Slice(numIdx[:n], func(i, j int) bool {
		return numIdx[i] > numIdx[j];
	})

	ans := int64(1);
	remainingK := k;
	for i := 0; i < n && remainingK > 0; i++ {
		nI := numIdx[i];
		x := int(nI >> 20);
		idx := int(nI & ((1 << 20) - 1));
		exp := min(int64((idx-left[idx])*(right[idx]-idx)), int64(remainingK));
		ans = (ans * modPow(int64(x), int(exp))) % MOD;
		remainingK -= int(exp);
	}

	return int(ans);
}

// Helper function to find minimum
func min(a, b int64) int64 {
	if a < b {
		return a;
	}

	return b;
}