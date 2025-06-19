# @param {Integer[]} nums
# @param {Integer} k
# @return {Integer}
def partition_array(nums, k)
   nums.sort!
    result = 0
    i = 0

    while i < nums.length
        start = nums[i]
        result += 1

        while i < nums.length && nums[i] - start <= k
            i += 1
        end
    end

    result
end