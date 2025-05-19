/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isBalanced(root *TreeNode) bool {
    return check(root) != -1;
}

func check(node *TreeNode) int {
	if node == nil {
		return 0;
	}

	left := check(node.Left);
	if left == -1 {
		return -1;
	}

	right := check(node.Right);
	if right == -1 {
		return -1;
	}

	if abs(left-right) > 1 {
		return -1;
	}

	return 1 + max(left, right);
}

func abs(x int) int {
	if x < 0 {
		return -x;
	}

	return x;
}

func max(a, b int) int {
	if a > b {
		return a;
	}
    
	return b;
}