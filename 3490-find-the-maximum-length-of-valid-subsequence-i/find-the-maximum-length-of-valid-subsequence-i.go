func maximumLength(nums []int) int {
	n := len(nums)
	if n == 2 {
		return 2
	}

	// z là parity (tính chẵn/lẻ) của số đầu tiên: true nếu lẻ, false nếu chẵn
	z := nums[0]%2 == 1

	// len[0]: độ dài dãy số toàn chẵn
	// len[1]: độ dài dãy số toàn lẻ
	// len[2]: độ dài dãy số chẵn lẻ xen kẽ (bắt đầu từ nums[0])
	lenArr := [3]int{}
	if z {
		lenArr[1] = 1
	} else {
		lenArr[0] = 1
	}
	lenArr[2] = 1

	for i := 1; i < n; i++ {
		x := nums[i]%2 == 1

		if x {
			lenArr[1]++
		} else {
			lenArr[0]++
		}

		if x != z {
			lenArr[2]++
			z = !z
		}
	}

	return max(lenArr[0], max(lenArr[1], lenArr[2]))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}