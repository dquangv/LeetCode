func countInterestingSubarrays(nums []int, modulo int, k int) int64 {
	n := len(nums);
	if k > n {
		return 0;
	}

	prefixCount := make([]int, n+1);
	prefixCount[0] = 1; // base case

	var result int64 = 0;
	prefixModSum := 0;

	for _, num := range nums {
		num %= modulo;
		if num == k {
			prefixModSum++;
		}

		prefixModSum %= modulo;

		targetMod := (prefixModSum - k + modulo) % modulo;

		if targetMod < 0 {
			targetMod += modulo;
		}

		if targetMod < n {
			result += int64(prefixCount[targetMod]);
		}

		prefixCount[prefixModSum]++;
	}

	return result;
}