# @param {Integer} num
# @return {Integer}
def max_diff(num)
  str = num.to_s

  # Tạo số lớn nhất: thay tất cả chữ số đầu tiên khác 9 bằng 9
  ch1 = str.chars.find { |c| c != '9' }
  max_str = str.tr(ch1 || '', '9')

  # Tạo số nhỏ nhất
  ch2 = if str[0] != '1'
          str[0]  # thay chữ số đầu tiên (nếu không phải 1) thành 1
        else
          str.chars[1..].find { |c| c != '0' && c != '1' }
        end

  min_replace = str[0] != '1' ? '1' : '0'
  min_str = ch2 ? str.tr(ch2, min_replace) : str

  max_num = max_str.to_i
  min_num = min_str.to_i

  max_num - min_num
end