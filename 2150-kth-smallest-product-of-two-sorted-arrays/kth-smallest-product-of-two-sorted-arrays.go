func kthSmallestProduct(nums1 []int, nums2 []int, k int64) int64 {
    sort.Ints(nums1)
    sort.Ints(nums2)

    lo, hi := int64(-1e10), int64(1e10)
    for lo < hi {
        mid := lo + (hi-lo)/2
        if countLessOrEqualProduct(nums1, nums2, mid) >= k {
            hi = mid
        } else {
            lo = mid + 1
        }
    }
    return lo
}

func countLessOrEqualProduct(nums1, nums2 []int, x int64) int64 {
    count := int64(0)
    for _, a := range nums1 {
        if a == 0 {
            if x >= 0 {
                count += int64(len(nums2))
            }
        } else if a > 0 {
            l, r := 0, len(nums2)
            for l < r {
                m := (l + r) / 2
                if int64(a)*int64(nums2[m]) <= x {
                    l = m + 1
                } else {
                    r = m
                }
            }
            count += int64(l)
        } else {
            l, r := 0, len(nums2)
            for l < r {
                m := (l + r) / 2
                if int64(a)*int64(nums2[m]) <= x {
                    r = m
                } else {
                    l = m + 1
                }
            }
            count += int64(len(nums2) - l)
        }
    }
    return count
}
