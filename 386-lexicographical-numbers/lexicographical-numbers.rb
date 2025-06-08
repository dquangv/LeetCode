# @param {Integer} n
# @return {Integer[]}
def lexical_order(n)
    result = []
    count = 1

    n.times do
        result << count;

        if count * 10 <= n
            count *= 10
        else
            while count % 10 == 9 || count + 1 > n
                count /= 10
            end
            count += 1
        end
    end

    result
end