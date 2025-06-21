def minimum_deletions(word, k)
  freq = Array.new(26, 0)

  # Đếm tần suất mỗi ký tự
  word.each_char { |ch| freq[ch.ord - 'a'.ord] += 1 }

  min_del = Float::INFINITY

  # Thử mỗi ký tự làm chuẩn so sánh
  26.times do |base|
    next if freq[base] == 0

    del = 0
    base_freq = freq[base]

    26.times do |comp|
      next if freq[comp] == 0 || base == comp

      comp_freq = freq[comp]

      if comp_freq < base_freq
        del += comp_freq
      elsif comp_freq - base_freq > k
        del += comp_freq - base_freq - k
      end
    end

    min_del = [min_del, del].min
  end

  min_del
end
