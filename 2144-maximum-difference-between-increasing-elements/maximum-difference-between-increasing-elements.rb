# @param {Integer[]} nums
# @return {Integer}
def maximum_difference(nums)
    min = nums[0]
    max = 0

    (1...nums.length).each do |i|
        if min > nums[i]
            min = nums[i]
        else
            max = [max, nums[i] - min].max
        end
    end
    
    max != 0 ? max : -1
end