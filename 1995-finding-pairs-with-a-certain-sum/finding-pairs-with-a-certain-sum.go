type FindSumPairs struct {
    nums1 []int
	nums2 []int
	freq  map[int]int
	xMax  int
}


func Constructor(nums1 []int, nums2 []int) FindSumPairs {
    freq := make(map[int]int)
	xMax := 0

	for _, x := range nums2 {
		freq[x]++
		if x > xMax {
			xMax = x
		}
	}

	sort.Ints(nums1)

	return FindSumPairs{
		nums1: nums1,
		nums2: nums2,
		freq:  freq,
		xMax:  xMax,
	}
}


func (this *FindSumPairs) Add(index int, val int)  {
    oldVal := this.nums2[index]
	this.freq[oldVal]--
	if this.freq[oldVal] == 0 {
		delete(this.freq, oldVal)
	}

	this.nums2[index] += val
	newVal := this.nums2[index]
	this.freq[newVal]++
	if newVal > this.xMax {
		this.xMax = newVal
	}
}


func (this *FindSumPairs) Count(tot int) int {
    count := 0
	threshold := max(1, tot-this.xMax)
	i0 := sort.SearchInts(this.nums1, threshold)

	for i := i0; i < len(this.nums1); i++ {
		y := this.nums1[i]
		if y >= tot {
			break
		}
		count += this.freq[tot-y]
	}
	return count
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}


/**
 * Your FindSumPairs object will be instantiated and called as such:
 * obj := Constructor(nums1, nums2);
 * obj.Add(index,val);
 * param_2 := obj.Count(tot);
 */