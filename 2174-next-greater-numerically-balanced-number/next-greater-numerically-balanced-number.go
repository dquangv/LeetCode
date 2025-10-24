func isBeautiful(count [10]int) bool {
	for d := 1; d <= 7; d++ {
		if count[d] != 0 && count[d] != d {
			return false
		}
	}

	return true
}

func generate(num int, count [10]int, list *[]int) {
	if num > 0 && isBeautiful(count) {
		*list = append(*list, num)
	}

	if num > 1224444 {
		return
	}

	for d := 1; d <= 7; d++ {
		if count[d] < d {
			count[d]++
			generate(num*10+d, count, list)
			count[d]--
		}
	}
}

func nextBeautifulNumber(n int) int {
	list := []int{}
	generate(0, [10]int{}, &list)
	ans := -1
    
	for _, num := range list {
		if num > n && (ans == -1 || num < ans) {
			ans = num
		}
	}

	return ans
}