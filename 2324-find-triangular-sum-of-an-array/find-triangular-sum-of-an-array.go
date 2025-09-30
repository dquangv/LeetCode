// Store existing Pascal triangle row values in a global factor map
// Since each index has a predetermined value, we do't have to worry about conflicting writes
var globalFactorMap = map[int][]int{
	1: []int{1}, // initialize
}

func triangularSum(nums []int) int {
	// Get the total sum and modulo by 10
	return getSumHelper(nums) % 10
}

func getSumHelper(nums []int) int {
	n := len(nums)

	// If we already have that row of the Pascal triangle, use those values
	// Note: Could just replace this with a combination!
	factors, ok := globalFactorMap[n]
	if ok {
		var sum int
		for i := range nums {
			// Multiple the nth row's i-th value with the current number
			sum += nums[i] * factors[i]
		}
		return sum
	}

	// If we have the n-1 row of the Pascal triangle, use that to get this row
	if prevFactors, ok := globalFactorMap[n-1]; ok {
		globalFactorMap[n] = make([]int, n)
		for i := range globalFactorMap[n] {
			var factor int
			if i > 0 {
				factor += prevFactors[i-1]
			}
			if i < n-1 {
				factor += prevFactors[i]
			}
			globalFactorMap[n][i] = factor % 10 // ensures the factors do not exceed max int
		}
	}

	// If we do not have any factor information yet, recursively go through each piece of nums
	return getSumHelper(nums[:len(nums)-1]) + getSumHelper(nums[1:])
}