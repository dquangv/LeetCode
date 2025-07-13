class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        
        int count = 0;  // Đếm số lượng ghép cặp hợp lệ
        int i = 0; // Con trỏ cho mảng players
        int j = 0; // Con trỏ cho mảng trainers
        
        // Duyệt cho đến khi hết players hoặc hết trainers
        while (i < players.length && j < trainers.length)
            if (players[i] <= trainers[j]) {
                // Nếu người chơi i có thể ghép với huấn luyện viên j
                // => tăng số lượng cặp và chuyển sang người chơi và huấn luyện viên tiếp theo
                count++;
                i++;
                j++;
            } else
                // Nếu huấn luyện viên j không đủ trình độ huấn luyện player i
                // => thử huấn luyện viên kế tiếp
                j++;

        return count;
    }
}
