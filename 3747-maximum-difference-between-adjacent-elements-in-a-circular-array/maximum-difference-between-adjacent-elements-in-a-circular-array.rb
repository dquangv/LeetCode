# @param {Integer[]} nums
# @return {Integer}
def max_adjacent_distance(nums)
    result = (nums[0] - nums[-1]).abs
    (0...nums.length - 1).each do |i|
        result = [(nums[i] - nums[i + 1]).abs, result].max
    end
    result
end