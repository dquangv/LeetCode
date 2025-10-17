func maxPartitionsAfterOperations(s string, k int) int {
	n := len(s)
	setBits := make([]int, n)
	for i, ch := range s {
		setBits[i] = 1 << (ch - 'a')
	}

	makePrefix := func(setBits []int) ([]int, []int) {
		prefix := make([]int, 1, len(setBits)+1)
		prefixMask := make([]int, 1, len(setBits)+1)
		mask := 0
		groups := 0
		for _, current := range setBits {
			mask |= current
			if bitCount(mask) > k {
				groups++
				mask = current
			}
			prefix = append(prefix, groups)
			prefixMask = append(prefixMask, mask)
		}
		return prefix, prefixMask
	}

	prefix, prefixMask := makePrefix(setBits)
	reversedSetBits := reverseInts(setBits)
	suffix, suffixMask := makePrefix(reversedSetBits)

	maxPartitions := 0
	for i := 0; i < n; i++ {
		candidate := prefix[i] + suffix[len(suffix)-(i+2)]
		mask := prefixMask[i] | suffixMask[len(suffixMask)-(i+2)]
		if min(bitCount(mask)+1, 26) <= k {
			candidate += 1
		} else if bitCount(prefixMask[i]) == k && bitCount(suffixMask[len(suffixMask)-(i+2)]) == k && bitCount(mask) < 26 {
			candidate += 3
		} else {
			candidate += 2
		}
		if candidate > maxPartitions {
			maxPartitions = candidate
		}
	}

	return maxPartitions
}

func bitCount(x int) int {
	count := 0
	for x > 0 {
		x &= x - 1
		count++
	}
	return count
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func reverseInts(arr []int) []int {
	n := len(arr)
	res := make([]int, n)
	for i := range arr {
		res[n-1-i] = arr[i]
	}
	return res
}