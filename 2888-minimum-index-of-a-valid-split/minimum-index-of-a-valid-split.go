func minimumIndex(nums []int) int {
    dominant, count := -1, 0;

    for _, num := range nums {
        if count == 0 {
            dominant = num;
            count++;
        } else if num == dominant {
            count++;
        } else {
            count--;
        }
    }

    maxCount := 0;

    for _, num := range nums {
        if num == dominant {
            maxCount++;
        }
    }

    countDominant := 0;

    for i := 0; i < len(nums); i++ {
        if nums[i] == dominant {
            countDominant++;
        }

        if countDominant * 2 > i + 1 && (maxCount - countDominant) * 2 > len(nums) - i - 1 {
            return i;
        }
    }

    return -1;
}