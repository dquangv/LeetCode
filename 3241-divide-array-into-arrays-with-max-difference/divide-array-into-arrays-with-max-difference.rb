# @param {Integer[]} nums
# @param {Integer} k
# @return {Integer[][]}
def divide_array(nums, k)
  nums.sort!
  return [] if nums.size % 3 != 0

  result = []
  (0...nums.size).step(3) do |i|
    group = nums[i, 3]
    return [] if group[2] - group[0] > k
    result << group
  end
  result
end
