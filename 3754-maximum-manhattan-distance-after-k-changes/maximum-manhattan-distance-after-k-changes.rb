# @param {String} s
# @param {Integer} k
# @return {Integer}
def max_distance(s, k)
    north = south = east = west = 0
    max_dist = 0

    s.chars.each_with_index do |dir, i|
        case dir
        when 'N'
            north += 1
        when 'S'
            south += 1
        when 'E'
            east += 1
        when 'W'
            west += 1
        end

        base = (north - south).abs + (east - west).abs
        extra = [2 * k, i + 1 - base].min
        total = base + extra

        max_dist = [max_dist, total].max
    end

    max_dist
end