class Solution { 
    public int maxDistance(String s, int k) {
        // Biến đếm số lần đi về mỗi hướng
        int north = 0, south = 0, east = 0, west = 0, maxDist = 0;

        for (int i = 0; i < s.length(); i++) {
            char dir = s.charAt(i);

            // Cập nhật số bước đi mỗi hướng
            if (dir == 'N')
                north++;
            else if (dir == 'S')
                south++;
            else if (dir == 'E')
                east++;
            else 
                west++;
            
            // Tính vị trí hiện tại:
            // x = east - west, y = north - south
            // Manhattan distance là tổng khoảng cách |x| + |y|
            int base = Math.abs(north - south) + Math.abs(east - west);

            // Ta có thể thay đổi tối đa k bước => mỗi bước có thể thay đổi vị trí theo 2 đơn vị (1 bước bị đổi từ hướng ngược sang hướng thẳng)
            // Nhưng tổng số bước tính đến thời điểm này là i + 1, nên không thể thay đổi vượt quá số bước đó
            // Lấy min giữa 2*k và số bước có thể thay đổi so với base
            int extra = Math.min(2 * k, i + 1 - base);

            int total = base + extra; // tổng khoảng cách đạt được sau khi thay đổi tối ưu

            // Cập nhật khoảng cách lớn nhất
            maxDist = Math.max(maxDist, total);
        }

        return maxDist;
    }
}
