func minSubarray(nums []int, p int) int {
	total := 0
	for _, x := range nums {
		total += x
	}
	need := total % p
	if need == 0 {
		return 0
	}

	mp := map[int]int{0: -1}
	prefix := 0
	res := len(nums)

	for i, x := range nums {
		prefix = (prefix + x) % p
		target := (prefix - need + p) % p
		if idx, ok := mp[target]; ok {
			if i-idx < res {
				res = i - idx
			}
		}
		mp[prefix] = i
	}

	if res == len(nums) {
		return -1
	}
	return res
}