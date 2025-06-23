# @param {Integer} k
# @param {Integer} n
# @return {Integer}
def k_mirror(k, n)
  sum = 0
  len = 1

  while n > 0
    (len...(len * 10)).each do |i|
      break if n <= 0
      p = create_palindrome(i, true)
      if is_palindrome?(p, k)
        sum += p
        n -= 1
      end
    end

    (len...(len * 10)).each do |i|
      break if n <= 0
      p = create_palindrome(i, false)
      if is_palindrome?(p, k)
        sum += p
        n -= 1
      end
    end

    len *= 10
  end

  sum
end

def create_palindrome(num, odd)
  x = num
  x /= 10 if odd
  while x > 0
    num = num * 10 + x % 10
    x /= 10
  end
  num
end

def is_palindrome?(num, base)
  digits = []
  while num > 0
    digits << num % base
    num /= base
  end
  digits == digits.reverse
end
