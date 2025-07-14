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
        // Initialize power multiplier
        int count = 1, result = 0;
        ListNode cur = head;

        // First pass: find the length of the list to determine the initial power of 2
        while (cur != null) {
            cur = cur.next;
            count *= 2;
        }

        // Adjust count (it will be one step too far after last iteration)
        count /= 2;
        while (head != null) {
            result += head.val * count;// Add the current bit's decimal contribution
            count /= 2;
            head = head.next;
        }

        return result;
    }
}