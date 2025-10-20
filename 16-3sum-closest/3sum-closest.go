func Abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func threeSumClosest(nums []int, target int) int {
	if len(nums) == 3 {
		return nums[0] + nums[1] + nums[2]
	}

	closestSum := 0
	closestDiff := 0

	madeSum := false

	for i := 0; i < len(nums); i++ {
		for j := i + 1; j < len(nums); j++ {
			firstSum := nums[i] + nums[j]

			for k := j + 1; k < len(nums); k++ {
				sum := firstSum + nums[k]
				diff := Abs(target - sum)

				if !madeSum || diff < closestDiff {
					closestSum = sum
					closestDiff = diff
					madeSum = true
				}

				if diff == 0 {
					return closestSum
				}
			}
		}
	}

	return closestSum
}