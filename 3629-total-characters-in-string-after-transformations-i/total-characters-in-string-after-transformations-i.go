func lengthAfterTransformations(s string, t int) int {
	const MOD = int64(1e9 + 7);
	count := make([]int64, 26);

	for _, c := range s {
		count[c-'a']++;
	}

	for t >= 26 {
		z := count[25];

		for i := 25; i > 0; i-- {
			count[i] = (count[i] + count[i-1]) % MOD;
		}

		count[0] = (count[0] + z) % MOD;
		count[1] = (count[1] + z) % MOD;

		t -= 26;
	}

	var result int64 = 0;
	for i := 0; i < 26; i++ {
		result = (result + count[i]) % MOD;
	}

	for i := 26 - t; i < 26; i++ {
		result = (result + count[i]) % MOD;
	}

	return int(result);
}