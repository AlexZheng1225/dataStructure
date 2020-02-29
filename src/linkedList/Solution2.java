package linkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Solution 2 ：使用虚拟头节点进行解决
class Solution2 {

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;   //将dummyHead放在head之前，使其成为链表的第一个元素

        ListNode prev = dummyHead;
        while(prev.next!=null){
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
//                prev.next = prev.next.next;  //不考虑对象游离时，可以直接将prev.next指向其的下一个结点
            }else{
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,6,2,3,9,2,5,2};

        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution2().removeElements(head,2);
        System.out.println(head);
    }
}