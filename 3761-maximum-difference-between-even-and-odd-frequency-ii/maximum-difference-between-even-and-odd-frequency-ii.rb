# @param {String} s
# @param {Integer} k
# @return {Integer}
def max_difference(s, k)
  n = s.length
  pre_sum = Array.new(n + 1) { Array.new(5, 0) }

  n.times do |i|
    (0..4).each { |j| pre_sum[i + 1][j] = pre_sum[i][j] }
    pre_sum[i + 1][s[i].ord - '0'.ord] += 1
  end

  max = -Float::INFINITY

  (0..4).each do |a|
    (0..4).each do |b|
      next if a == b

      left = 0
      right = 0
      min = Array.new(2) { Array.new(2, Float::INFINITY) }

      while right < n
        while right - left + 1 >= k &&
              pre_sum[right + 1][a] > pre_sum[left][a] &&
              pre_sum[right + 1][b] > pre_sum[left][b]

          p = pre_sum[left][a] % 2
          q = pre_sum[left][b] % 2
          min[p][q] = [min[p][q], pre_sum[left][a] - pre_sum[left][b]].min
          left += 1
        end

        x = (pre_sum[right + 1][a] % 2) ^ 1
        y = pre_sum[right + 1][b] % 2
        max = [max, pre_sum[right + 1][a] - pre_sum[right + 1][b] - min[x][y]].max
        right += 1
      end
    end
  end

  max.to_i
end
