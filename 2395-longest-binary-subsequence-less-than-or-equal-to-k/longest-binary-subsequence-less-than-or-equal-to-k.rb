# @param {String} s
# @param {Integer} k
# @return {Integer}
def longest_subsequence(s, k)
  sum = 0
  length = 0
  max_bits = Math.log2(k).to_i + 1

  s.chars.reverse.each_with_index do |bit, i|
    if bit == '1'
      if i < max_bits && sum + (1 << i) <= k
        sum += 1 << i
        length += 1
      end
    else
      length += 1
    end
  end

  length
end
