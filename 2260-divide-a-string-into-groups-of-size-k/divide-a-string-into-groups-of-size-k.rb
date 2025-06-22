# @param {String} s
# @param {Integer} k
# @param {Character} fill
# @return {String[]}
def divide_string(s, k, fill)
    result = []

    (0...s.length).step(k) do |i|
        group = s[i, k]
        group = group.ljust(k, fill) if group.length < k
        result << group
    end

    result
end