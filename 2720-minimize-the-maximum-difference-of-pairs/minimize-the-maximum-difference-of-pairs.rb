# @param {Integer[]} nums
# @param {Integer} p
# @return {Integer}
def minimize_max(nums, p)
    nums.sort!
    binary_search(nums, p)
end

def can_form_pairs(nums, d, p)
    count = 0
    i = 0
    while i < nums.size - 1
        if nums[i + 1] - nums[i]  <= d
            count += 1
            i += 1
        end
        return true if count >= p
        i += 1
    end
    false
end

def binary_search(nums, p)
    low = 0
    high = nums[-1] - nums[0]

    while low < high
        mid = (low + high) / 2
        if can_form_pairs(nums, mid, p)
            high = mid
        else
            low = mid + 1
        end
    end
    low
end