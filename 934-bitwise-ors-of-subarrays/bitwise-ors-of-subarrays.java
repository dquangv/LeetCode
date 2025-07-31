class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        // Tập hợp dùng để lưu kết quả cuối cùng gồm tất cả giá trị OR khác nhau của các subarray
        Set<Integer> finalSet = new HashSet<>();

        // Tập hợp `set` lưu tất cả kết quả OR có thể có từ các subarray kết thúc tại chỉ số trước đó
        Set<Integer> set = new HashSet<>() {
            {
                add(arr[0]); // Khởi tạo với phần tử đầu tiên
            }
        };
        finalSet.add(arr[0]); // Thêm phần tử đầu tiên vào kết quả

        // Duyệt từ phần tử thứ hai đến hết mảng
        for (int i = 1; i < arr.length; i++) {
            Set<Integer> newSet = new HashSet<>();

            // Bắt đầu subarray mới chỉ chứa arr[i]
            newSet.add(arr[i]);
            finalSet.add(arr[i]); // Thêm vào kết quả

            // Với mỗi kết quả OR từ các subarray kết thúc trước đó,
            // thực hiện OR với arr[i] để tạo subarray mới kết thúc tại i
            for (Integer j : set) {
                int val = j | arr[i]; // Bitwise OR
                newSet.add(val); // Thêm vào newSet
                finalSet.add(val); // Thêm vào kết quả cuối cùng
            }

            // Cập nhật set cho vòng lặp tiếp theo
            set = newSet;
        }

        return finalSet.size();
    }
}