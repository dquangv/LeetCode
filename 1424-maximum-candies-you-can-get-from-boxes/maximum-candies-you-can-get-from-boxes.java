class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasKey = new boolean[n]; // Đánh dấu những hộp mà ta đã có chìa khóa
        boolean[] hasBox = new boolean[n]; // Đánh dấu những hộp mà ta đang sở hữu (đã lấy được)
        boolean[] opened = new boolean[n]; // Đánh dấu hộp đã mở để tránh xử lý lại

        Queue<Integer> queue = new LinkedList<>(); // Hàng đợi các hộp có thể xử lý
        int totalCandies = 0;

        // Khởi tạo: đánh dấu hộp đã sở hữu, nếu hộp mở thì cho vào queue luôn
        for (int box : initialBoxes) {
            hasBox[box] = true;
            if (status[box] == 1) {
                queue.offer(box);
                opened[box] = true;
            }
        }

        while (!queue.isEmpty()) {
            int currBox = queue.poll(); // Lấy hộp hiện tại ra xử lý

            totalCandies += candies[currBox]; // Nhặt kẹo

            // Nhặt các chìa khóa trong hộp này
            for (int key : keys[currBox]) {
                hasKey[key] = true;

                // Nếu trước đó ta có hộp này nhưng chưa mở được, giờ có key rồi thì mở
                if (hasBox[key] && !opened[key] && status[key] == 0) {
                    queue.offer(key);
                    opened[key] = true;
                }
            }

            // Nhặt các hộp nằm trong hộp này
            for (int boxInside : containedBoxes[currBox]) {
                hasBox[boxInside] = true;

                // Nếu hộp này đã mở hoặc có key thì thêm vào queue để xử lý
                if (status[boxInside] == 1 || hasKey[boxInside])
                    if (!opened[boxInside]) {
                        queue.offer(boxInside);
                        opened[boxInside] = true;
                    }
            }
        }

        return totalCandies;
    }
}