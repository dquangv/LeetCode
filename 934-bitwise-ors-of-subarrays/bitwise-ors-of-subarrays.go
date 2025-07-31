func subarrayBitwiseORs(arr []int) int {
	set := make(map[int]struct{}) // To store distinct OR values

	for i := 0; i < len(arr); i++ {
		set[arr[i]] = struct{}{} // Add the element itself

		// Go backward to update OR results for subarrays ending at i
		for j := i - 1; j >= 0; j-- {
			if (arr[j] | arr[i]) == arr[j] {
				break // Early exit if no change
			}
			arr[j] |= arr[i]
			set[arr[j]] = struct{}{}
		}
	}

	return len(set)
}
