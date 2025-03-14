func maximumCandies(candies []int, k int64) int {
    var total int64 = 0;
    
    for _, pile := range candies {
        total += int64(pile);
    }

    if total < k {
        return 0;
    }

    left := 1;
    right := int(total/k);
    result := 0;

    for left <= right {
        mid := (left + right) / 2

        if canDistribute(candies, mid, k) {
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}

func canDistribute(candies []int, val int, k int64) bool {
    var count int64 = 0;

    for _, pile := range candies {
        count += int64(pile/val);

        if count >= k {
            return true;
        }
    }

    return count >= k;
}