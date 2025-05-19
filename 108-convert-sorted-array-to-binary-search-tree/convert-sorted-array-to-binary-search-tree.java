/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    // Hàm đệ quy để xây cây từ đoạn nums[left...right]
    private TreeNode buildBST(int[] nums, int left, int right) {
        // Base case: không còn phần tử nào
        if (left > right)
            return null;

        // Chọn phần tử giữa để làm gốc → đảm bảo cân bằng
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // Đệ quy xây cây con trái từ đoạn [left...mid-1]
        root.left = buildBST(nums, left, mid - 1);

        // Đệ quy xây cây con phải từ đoạn [mid+1...right]
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }
}