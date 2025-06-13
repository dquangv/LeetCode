func minimizeMax(nums []int, p int) int {
	sort.Ints(nums)
	return binarySearch(nums, p)
}

// Kiểm tra có thể tạo được p cặp với hiệu <= d
func canFormPairs(nums []int, d int, p int) bool {
	count := 0
	for i := 0; i < len(nums)-1; i++ {
		if nums[i+1]-nums[i] <= d {
			count++
			i++ // bỏ qua phần tử thứ i+1 để tránh trùng chỉ số
		}
		if count >= p {
			return true
		}
	}
	return false
}

// Binary search tìm min của max hiệu
func binarySearch(nums []int, p int) int {
	low := 0
	high := nums[len(nums)-1] - nums[0]

	for low < high {
		mid := low + (high-low)/2
		if canFormPairs(nums, mid, p) {
			high = mid
		} else {
			low = mid + 1
		}
	}
	return low
}