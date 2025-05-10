func minSum(nums1 []int, nums2 []int) int64 {
    count1, count2, sum1, sum2 := 0, 0, 0, 0;

    for _, num := range nums1 {
        sum1 += num;
        if num == 0 {
            count1++;
        }
    }

    for _, num := range nums2 {
        sum2 += num;
        if num == 0 {
            count2++;
        }
    }

    if (count1 == 0 && sum1 < sum2 + count2) || (count2 == 0 && sum2 < sum1 + count1) {
        return -1;
    }

    if sum1 + count1 > sum2 + count2 {
        return int64(sum1 + count1);
    }

    return int64(sum2 + count2);
}