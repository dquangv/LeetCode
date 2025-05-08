/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

func deleteDuplicates(head *ListNode) *ListNode {
    res := head;

	// Duyệt qua danh sách
	for head != nil && head.Next != nil {
		if head.Val == head.Next.Val {
			head.Next = head.Next.Next; // Bỏ qua node trùng
		} else {
			head = head.Next; // Di chuyển tới node tiếp theo
		}
	}

	return res;
}