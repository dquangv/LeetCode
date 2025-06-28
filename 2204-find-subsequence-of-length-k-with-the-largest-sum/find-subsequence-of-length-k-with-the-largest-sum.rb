# @param {Integer[]} nums
# @param {Integer} k
# @return {Integer[]}
def max_subsequence(nums, k)
    # Mỗi phần tử là [giá trị, chỉ số]
    indexed = nums.each_with_index.map { |val, i| [val, i] }

    # Lấy k phần tử có giá trị lớn nhất (theo val)
    top_k = indexed.sort_by { |val, _| -val }[0...k]

    # Sắp xếp lại theo chỉ số ban đầu để giữ thứ tự subsequence
    sorted = top_k.sort_by { |_, i| i }

    # Trả về mảng kết quả
    sorted.map { |val, _| val }
end