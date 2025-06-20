func maxDistance(s string, k int) int {
	north, south, east, west, maxDist := 0, 0, 0, 0, 0

	for i := 0; i < len(s); i++ {
		dir := s[i]

		// Đếm số lần đi mỗi hướng
		switch dir {
		case 'N':
			north++
		case 'S':
			south++
		case 'E':
			east++
		case 'W':
			west++
		}

		// Tính khoảng cách hiện tại
		base := abs(north-south) + abs(east-west)

		// Tính phần cộng thêm do được thay đổi k ký tự
		extra := min(2*k, i+1-base)

		// Tổng khoảng cách có thể đạt được
		total := base + extra

		if total > maxDist {
			maxDist = total
		}
	}

	return maxDist
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
