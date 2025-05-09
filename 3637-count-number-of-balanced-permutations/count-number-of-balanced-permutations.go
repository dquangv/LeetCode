const mod = int(1e9 + 7);

var comb = pascalTriangle();

// Pascal triangle C(n, k) % mod
func pascalTriangle() [41][41]int {
	var a [41][41]int;
	for i := 0; i < 41; i++ {
		a[i][0], a[i][i] = 1, 1;
		for j := 1; j <= i/2; j++ {
			x := a[i-1][j-1] + a[i-1][j];
			if x >= mod {
				x -= mod;
			}
			a[i][j] = x;
			a[i][i-j] = x;
		}
	}

	return a;
}

type Solver struct {
	sum   int;
	n     int;
	freq  [10]int;
	dp    map[uint]int;
}

func (s *Solver) f(i, even, odd, remain int) int {
	if even == 0 && odd == 0 && remain == 0 {
		return 1;
	}

	if i < 0 || even < 0 || odd < 0 || remain < 0 {
		return 0;
	}

	key := uint(i<<21 | even<<15 | odd<<9 | remain);
	if val, ok := s.dp[key]; ok {
		return val;
	}

	ans := 0;
	maxJ := s.freq[i];
	if maxJ > even {
		maxJ = even;
	}

	for j := 0; j <= maxJ; j++ {
		oddRem := s.freq[i] - j;
		if oddRem <= odd && remain >= i*j {
			// prune
			if remain-i*j > (even-j+odd-oddRem)*(i-1) {
				continue;
			}

			val := comb[even][j] * comb[odd][oddRem] % mod * s.f(i-1, even-j, odd-oddRem, remain-i*j) % mod;
			ans = (ans + val) % mod;
		}
	}
    
	s.dp[key] = ans;
	return ans;
}

func countBalancedPermutations(num string) int {
	s := Solver{dp: make(map[uint]int)};
	s.n = len(num);
	for _, ch := range num {
		x := int(ch - '0');
		s.freq[x]++;
		s.sum += x;
	}

	if s.sum%2 != 0 {
		return 0;
	}

	s.sum /= 2;
	oddLen := s.n / 2;
	evenLen := s.n - oddLen;

	return s.f(9, evenLen, oddLen, s.sum);
}