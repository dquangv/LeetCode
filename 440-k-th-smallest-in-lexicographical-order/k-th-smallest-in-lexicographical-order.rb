# @param {Integer} n
# @param {Integer} k
# @return {Integer}
def find_kth_number(n, k)
    curr = 1
    k -= 1  # Đã dùng số đầu tiên rồi

    while k > 0
        steps = count_steps(n, curr, curr + 1)
        if steps <= k
        k -= steps
        curr += 1
        else
        k -= 1
        curr *= 10
        end
    end

    curr
end

# Đếm số lượng bước từ prefix curr đến next không vượt quá n
def count_steps(n, curr, nxt)
    steps = 0
    while curr <= n
        steps += [n + 1, nxt].min - curr
        curr *= 10
        nxt *= 10
    end
    steps
end