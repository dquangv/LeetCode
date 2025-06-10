# @param {String} s
# @return {Integer}
def max_difference(s)
    freq = Array.new(26, 0)

    s.each_char { |c| freq[c.ord - 'a'.ord] += 1}
    a1 = -Float::INFINITY
    a2 = Float::INFINITY

    freq.each do |f|
        next if f == 0

        if f.odd?
            a1 = [a1, f].max
        else
            a2 = [a2, f].min
        end
    end

    return -1 if a1 == -Float::INFINITY || a2 == Float::INFINITY

    a1 - a2
end