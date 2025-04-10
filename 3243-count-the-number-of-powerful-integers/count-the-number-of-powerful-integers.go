func numberOfPowerfulInt(start int64, finish int64, limit int, s string) int64 {
    suffix, _ := strconv.ParseInt(s, 10, 64);

	if finish < suffix {
		return 0;
	}

	lengthSuf := int64(len(s));
	powerOfTen := powInt64(10, int(lengthSuf));
	prefixStart := start / powerOfTen;
	prefixFinish := finish / powerOfTen;

	if start%powerOfTen > suffix {
		prefixStart++;
	}

	if finish%powerOfTen >= suffix {
		prefixFinish++;
	}

	return countValidPrefixes(prefixFinish, limit) - countValidPrefixes(prefixStart, limit);
}

func countValidPrefixes(num int64, limit int) int64 {
	if num == 0 {
		return 0;
	}
	if limit == 9 {
		return num;
	}

	var powLimit [20]int64;
	powLimit[0] = 1;
	for i := 1; i < 20; i++ {
		powLimit[i] = powLimit[i-1] * int64(limit+1);
	}

	var digits []int;
	n := num;
	for n > 0 {
		digits = append(digits, int(n % 10));
		n /= 10;
	}

	for i, j := 0, len(digits)-1; i < j; i, j = i+1, j-1 {
		digits[i], digits[j] = digits[j], digits[i];
	}

	var result int64;
	for i, d := range digits {
		if d > limit {
			return result + powLimit[len(digits) - i];
		}

		result += int64(d) * powLimit[len(digits) - i - 1];
	}

	return result;
}


func powInt64(base, exp int) int64 {
	result := int64(1);

	for i := 0; i < exp; i++ {
		result *= int64(base);
	}

	return result;
}
