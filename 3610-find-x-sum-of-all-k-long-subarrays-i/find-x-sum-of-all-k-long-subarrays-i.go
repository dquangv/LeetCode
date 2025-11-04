type Pair struct {
	First  int
	Second int
}

func findXSum(nums []int, k int, x int) []int {
	n := len(nums)

	ans := make([]int, n-k+1)
	for i := 0; i < len(ans); i++ {
		freq := make([]int, 51)
		for j := 0; j < k; j++ {
			freq[nums[i+j]]++
		}

		parr := make([]Pair, 0)
		for j := 0; j < 51; j++ {
			if freq[j] > 0 {
				parr = append(parr, Pair{freq[j], j})
			}
		}

		sort.Slice(parr, func(i, j int) bool {
			if parr[i].First == parr[j].First {
				return parr[i].Second > parr[j].Second
			}
			return parr[i].First > parr[j].First
		})

		sum := 0
		for j := 0; j < x; j++ {
			if j >= len(parr) {
				break
			}
			sum += parr[j].First * parr[j].Second
		}
		ans[i] = sum
	}

	return ans
}