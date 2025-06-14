# @param {Integer} num
# @return {Integer}
def min_max_difference(num)
    first_non_nine = nil
    first_digit = nil
    remaining = num

    while remaining > 0
        digit = remaining % 10
        first_non_nine = digit if digit != 9
        first_digit = digit
        remaining /= 10
    end

    remaining = num
    min_val = 0
    max_val = 0
    multiplier = 1

    # Tạo số min và max
    while remaining > 0
        digit = remaining % 10
        min_digit = digit
        max_digit = digit

        min_digit = 0 if digit == first_digit
        max_digit = 9 if digit == first_non_nine

        min_val += multiplier * min_digit
        max_val += multiplier * max_digit

        multiplier *= 10
        remaining /= 10
    end

    max_val - min_val
end