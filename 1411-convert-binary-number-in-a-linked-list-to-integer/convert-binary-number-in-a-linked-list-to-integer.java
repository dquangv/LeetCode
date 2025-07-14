/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int count = 1, result = 0;
        ListNode cur = head;

        while (cur != null) {
            cur = cur.next;
            count *= 2;
        }

        count /= 2;
        while (head != null) {
            result += head.val * count;
            count /= 2;
            head = head.next;
        }

        return result;
    }
}