# @param {Integer[]} nums
# @param {Integer} key
# @param {Integer} k
# @return {Integer[]}
def find_k_distant_indices(nums, key, k)
  n = nums.length
  diff = Array.new(n + 1, 0)
  result = []

  nums.each_with_index do |num, i|
    if num == key
      start = [0, i - k].max
      ending = [n, i + k + 1].min
      diff[start] += 1
      diff[ending] -= 1 if ending < n
    end
  end

  sum = 0
  (0...n).each do |i|
    sum += diff[i]
    result << i if sum > 0
  end

  result
end
