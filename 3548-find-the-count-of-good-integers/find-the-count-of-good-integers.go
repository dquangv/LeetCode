func factorial(n int64) *big.Int {
	res := big.NewInt(1);

	for i := int64(2); i <= n; i++ {
		res.Mul(res, big.NewInt(i));
	}

	return res;
}

func generatePalindromes(n, k int) []string {
	var result []string;
	var dfs func(i int, curr []byte);

	dfs = func(i int, curr []byte) {
		if i >= (n+1)/2 {
			if curr[0] == '0' {
				return;
			}

			num := new(big.Int);
			num.SetString(string(curr), 10);

			if new(big.Int).Mod(num, big.NewInt(int64(k))).Cmp(big.NewInt(0)) == 0 {
				result = append(result, string(curr));
			}

			return;
		}

		for c := byte('0'); c <= '9'; c++ {
			if i == 0 && c == '0' {
				continue;
			}

			curr[i], curr[n-1-i] = c, c;
			dfs(i+1, curr);
		}
	}

	dfs(0, make([]byte, n));
	return result;
}

func countGoodIntegers(n int, k int) int64 {
	validPalindromes := generatePalindromes(n, k);

	uniquePatterns := make(map[string]bool);

	for _, pal := range validPalindromes {
		freq := make([]int, 10);

		for _, ch := range pal {
			freq[ch-'0']++;
		}

		builder := strings.Builder{};

		for _, f := range freq {
			builder.WriteByte(byte('a' + f));
		}

		uniquePatterns[builder.String()] = true;
	}

	totalPerm := factorial(int64(n));
	totalValid := big.NewInt(0);

	for pattern := range uniquePatterns {
		perm := new(big.Int).Set(totalPerm);

		for i := 0; i < len(pattern); i++ {
			perm.Div(perm, factorial(int64(pattern[i]-'a')));
		}

		if pattern[0] != 'a' {
			zeroCount := int(pattern[0] - 'a');

			if zeroCount > 0 {
				invalid := factorial(int64(n - 1));

				for i := 1; i < len(pattern); i++ {
					invalid.Div(invalid, factorial(int64(pattern[i]-'a')));
				}

				invalid.Div(invalid, factorial(int64(zeroCount-1)));
				perm.Sub(perm, invalid);
			}
		}

		totalValid.Add(totalValid, perm);
	}

	return totalValid.Int64();
}