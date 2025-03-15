func minCapability(nums []int, k int) int {
    low, high := nums[0], nums[0];

    for _, num := range nums {
        if num < low {
            low = num
        }

        if num > high {
            high = num
        }
    }

    for low < high {
        mid := (low + high) / 2;

        if canRob(nums, mid, k) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }

    return low;
}

func canRob (nums []int, capability int, k int) bool {
    count, i := 0, 0;

    for i < len(nums) {
        if nums[i] <= capability {
            count++;
            i++;
        }

        i++;
    }

    return count >= k;
}