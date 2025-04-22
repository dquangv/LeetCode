const (
	MOD     = 1_000_000_007;
	MAX_N   = 10010;
	MAX_EXP = 15;
)

var (
	comb          [MAX_N + MAX_EXP][MAX_EXP + 1]int;
	smallestPrime [MAX_N]int;
	primeExps     [MAX_N][]int;
	initialized   = false;
)

func initCombinatoricsAndSieve() {
	if initialized {
		return;
	}

	initialized = true;

	// Phân tích thừa số nguyên tố
	for i := 2; i < MAX_N; i++ {
		if smallestPrime[i] == 0 {
			for j := i; j < MAX_N; j += i {
				if smallestPrime[j] == 0 {
					smallestPrime[j] = i;
				}
			}
		}
	}

	for i := 2; i < MAX_N; i++ {
		x := i;
		var exps []int;

		for x > 1 {
			p := smallestPrime[x];
			cnt := 0;

			for x%p == 0 {
				x /= p;
				cnt++;
			}

			exps = append(exps, cnt);
		}

		primeExps[i] = exps;
	}

	// Tính tổ hợp C(n, k)
	for i := 0; i < MAX_N+MAX_EXP; i++ {
		comb[i][0] = 1;

		for j := 1; j <= MAX_EXP && j <= i; j++ {
			comb[i][j] = (comb[i-1][j] + comb[i-1][j-1]) % MOD;
		}
	}
}

func idealArrays(n int, maxValue int) int {
	initCombinatoricsAndSieve();

	ans := 0;
	for x := 1; x <= maxValue; x++ {
		ways := 1;

		for _, e := range primeExps[x] {
			ways = (ways * comb[n+e-1][e]) % MOD;
		}

		ans = (ans + ways) % MOD;
	}

	return ans;
}