class Solution {
    public long distributeCandies(int n, int limit) {
        // Sử dụng nguyên lý bao gồm - loại trừ (Inclusion-Exclusion)
        // Bước 1: Tổng số bộ ba không âm (a + b + c = n)
        // Bước 2: Trừ đi các trường hợp có ít nhất 1 biến vượt quá limit
        // Bước 3: Cộng lại các trường hợp có 2 biến vượt limit (bị trừ hai lần ở bước 2)
        // Bước 4: Trừ lại trường hợp cả 3 biến đều vượt limit (bị cộng dư)

        return combCount(n) // Tất cả các bộ (a + b + c = n) không giới hạn
                - 3 * combCount(n - (limit + 1)) // Trừ các trường hợp có 1 biến > limit
                + 3 * combCount(n - 2 * (limit + 1)) // Cộng lại các TH có 2 biến > limit (bị trừ 2 lần)
                - combCount(n - 3 * (limit + 1)); // Trừ lại TH cả 3 biến đều > limit (bị cộng 3 lần)
    }

    // Hàm tính số cách chọn bộ ba số không âm (a + b + c = sum)
    // Dựa vào tổ hợp C(sum + 2, 2) = (sum + 2)(sum + 1) / 2
    private long combCount(long sum) {
        if (sum < 0)
            return 0; // Nếu sum âm thì không có cách nào hợp lệ
        return (sum + 2) * (sum + 1) / 2;
    }
}
